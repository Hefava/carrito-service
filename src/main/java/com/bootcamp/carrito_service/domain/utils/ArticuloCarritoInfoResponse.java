package com.bootcamp.carrito_service.domain.utils;

import java.util.List;

public class ArticuloCarritoInfoResponse {
    private double precioTotal;
    private List<ArticuloCarritoInfo> articulos;
    private int page;
    private int pageSize;
    private int totalPages;
    private long totalCount;

    public ArticuloCarritoInfoResponse() {
    }

    public ArticuloCarritoInfoResponse(double precioTotal, List<ArticuloCarritoInfo> articulos, int page, int pageSize, int totalPages, long totalCount) {
        this.precioTotal = precioTotal;
        this.articulos = articulos;
        this.page = page;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.totalCount = totalCount;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public List<ArticuloCarritoInfo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<ArticuloCarritoInfo> articulos) {
        this.articulos = articulos;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}