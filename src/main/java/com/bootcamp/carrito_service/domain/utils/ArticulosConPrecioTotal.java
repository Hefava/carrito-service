package com.bootcamp.carrito_service.domain.utils;

import java.math.BigDecimal;
import java.util.List;

public class ArticulosConPrecioTotal {
    private BigDecimal precioTotal;
    private List<ArticuloCarritoInfoResponse> articulos;
    private int page;
    private int pageSize;
    private int totalPages;
    private long totalCount;

    public ArticulosConPrecioTotal() {
    }

    public ArticulosConPrecioTotal(BigDecimal precioTotal, List<ArticuloCarritoInfoResponse> articulos, int page, int pageSize, int totalPages, long totalCount) {
        this.precioTotal = precioTotal;
        this.articulos = articulos;
        this.page = page;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.totalCount = totalCount;
    }

    public BigDecimal getPrecioTotal() {
        return precioTotal;
    }

    public List<ArticuloCarritoInfoResponse> getArticulos() {
        return articulos;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setPrecioTotal(BigDecimal precioTotal) {
        this.precioTotal = precioTotal;
    }

    public void setArticulos(List<ArticuloCarritoInfoResponse> articulos) {
        this.articulos = articulos;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}