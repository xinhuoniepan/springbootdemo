package com.example.springbootdemo.bean;

public class DocumentRecord {
    private Integer id;

    private String documentName;

    private String documentDescription;

    private String documentUrl;

    private String documentType;

    public DocumentRecord(Integer id, String documentName, String documentDescription, String documentUrl, String documentType) {
        this.id = id;
        this.documentName = documentName;
        this.documentDescription = documentDescription;
        this.documentUrl = documentUrl;
        this.documentType = documentType;
    }

    public DocumentRecord() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName == null ? null : documentName.trim();
    }

    public String getDocumentDescription() {
        return documentDescription;
    }

    public void setDocumentDescription(String documentDescription) {
        this.documentDescription = documentDescription == null ? null : documentDescription.trim();
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl == null ? null : documentUrl.trim();
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType == null ? null : documentType.trim();
    }
}