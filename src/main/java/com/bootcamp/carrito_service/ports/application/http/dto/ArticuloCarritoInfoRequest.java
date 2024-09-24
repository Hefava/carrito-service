package com.bootcamp.carrito_service.ports.application.http.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticuloCarritoInfoRequest {
    private List<Long> articuloIds;
    private String sortBy;
    private String order;
    private String categoriaNombre;
    private String marcaNombre;
    private PageableRequest pageable;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PageableRequest {
        private int pageNumber;
        private int pageSize;
    }
}
