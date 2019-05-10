package com.basejava.webapp.exception;

public class NotExistStorageException extends StorageException {

    public NotExistStorageException(String uuid) {
        super("Unknown uuid! Couldn't find resume \"" + uuid + "\"!", uuid);
    }
}
