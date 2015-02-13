package com.dharmab.sheets.server.database;

import java.io.Serializable;

public interface HasIdAndVersion<I extends Serializable, V> {
    public I getId();

    public V getVersion();
}
