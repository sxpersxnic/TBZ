package com.github.sxpersxnic.tbz.m320.model;

import com.github.sxpersxnic.tbz.m320.lib.interfaces.Page;
import com.github.sxpersxnic.tbz.m320.lib.interfaces.PageItem;

import java.util.Set;

public class PageImpl implements Page {

    private Set<PageItem> items;

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public Set<PageItem> getItems() {
        return items;
    }

    @Override
    public void setItems(Set<PageItem> items) {
        this.items = items;
    }
}
