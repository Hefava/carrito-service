package com.bootcamp.carrito_service.domain.utils;

import java.util.List;

public class ArticuloRequest {
    private List<Long> articuloIds;
    private String sortBy;
    private String order;
    private String categoriaNombre;
    private String marcaNombre;
    private Pageable pageable;

    public ArticuloRequest() {
    }

    public ArticuloRequest(List<Long> articuloIds, String sortBy, String order, String categoriaNombre, String marcaNombre, Pageable pageable) {
        this.articuloIds = articuloIds;
        this.sortBy = sortBy;
        this.order = order;
        this.categoriaNombre = categoriaNombre;
        this.marcaNombre = marcaNombre;
        this.pageable = pageable;
    }

    public List<Long> getArticuloIds() {
        return articuloIds;
    }

    public String getSortBy() {
        return sortBy;
    }

    public String getOrder() {
        return order;
    }

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public String getMarcaNombre() {
        return marcaNombre;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setArticuloIds(List<Long> articuloIds) {
        this.articuloIds = articuloIds;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }

    public void setMarcaNombre(String marcaNombre) {
        this.marcaNombre = marcaNombre;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public static class Pageable {
        private int pageNumber;
        private int pageSize;

        public Pageable() {
        }

        public Pageable(int pageNumber, int pageSize) {
            this.pageNumber = pageNumber;
            this.pageSize = pageSize;
        }

        public int getPageNumber() {
            return pageNumber;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }
    }
}