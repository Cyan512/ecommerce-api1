package com.ecommerce.cart.application.usecase;

import com.ecommerce.cart.application.dto.WishlistResponse;
import com.ecommerce.cart.domain.model.Wishlist;
import com.ecommerce.cart.domain.model.WishlistItem;
import com.ecommerce.cart.domain.port.WishlistItemRepositoryPort;
import com.ecommerce.cart.domain.port.WishlistRepositoryPort;
import com.ecommerce.product.domain.model.Producto;
import com.ecommerce.product.domain.port.ProductoRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WishlistUseCase {

    private final WishlistRepositoryPort wishlistRepository;
    private final WishlistItemRepositoryPort wishlistItemRepository;
    private final ProductoRepositoryPort productoRepository;

    public WishlistUseCase(WishlistRepositoryPort wishlistRepository,
                           WishlistItemRepositoryPort wishlistItemRepository,
                           ProductoRepositoryPort productoRepository) {
        this.wishlistRepository = wishlistRepository;
        this.wishlistItemRepository = wishlistItemRepository;
        this.productoRepository = productoRepository;
    }

    public WishlistResponse getWishlist(UUID usuarioId) {
        Wishlist wishlist = wishlistRepository.findByUsuarioId(usuarioId)
                .orElseGet(() -> {
                    Wishlist w = new Wishlist();
                    w.setUsuarioId(usuarioId);
                    return wishlistRepository.save(w);
                });
        List<WishlistItem> items = wishlistItemRepository.findByWishlistId(wishlist.getId());
        return buildResponse(wishlist, items);
    }

    public WishlistResponse addItem(UUID usuarioId, UUID productoId) {
        Wishlist wishlist = wishlistRepository.findByUsuarioId(usuarioId)
                .orElseGet(() -> {
                    Wishlist w = new Wishlist();
                    w.setUsuarioId(usuarioId);
                    return wishlistRepository.save(w);
                });

        if (wishlistItemRepository.findByWishlistIdAndProductoId(wishlist.getId(), productoId).isEmpty()) {
            WishlistItem item = new WishlistItem();
            item.setWishlistId(wishlist.getId());
            item.setProductoId(productoId);
            wishlistItemRepository.save(item);
        }

        List<WishlistItem> items = wishlistItemRepository.findByWishlistId(wishlist.getId());
        return buildResponse(wishlist, items);
    }

    public void removeItem(UUID usuarioId, UUID itemId) {
        wishlistItemRepository.deleteById(itemId);
    }

    private WishlistResponse buildResponse(Wishlist wishlist, List<WishlistItem> items) {
        List<WishlistResponse.ItemResponse> itemResponses = items.stream().map(item -> {
            Producto producto = productoRepository.findById(item.getProductoId()).orElse(null);
            return new WishlistResponse.ItemResponse(
                    item.getId(), item.getProductoId(),
                    producto != null ? producto.getNombre() : "N/A");
        }).collect(Collectors.toList());
        return new WishlistResponse(wishlist.getId(), itemResponses);
    }
}
