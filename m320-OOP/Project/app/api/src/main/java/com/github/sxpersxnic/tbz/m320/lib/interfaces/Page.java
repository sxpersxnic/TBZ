package com.github.sxpersxnic.tbz.m320.lib.interfaces;

import java.util.Set;

public interface Page {
    void setItems(Set<PageItem> items);
    int getItemCount();
    Set<PageItem> getItems();
}
