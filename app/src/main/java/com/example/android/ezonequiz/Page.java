package com.example.android.ezonequiz;

/**
 * This is a container for holding information about each page.
 */
class Page {
    private int bodyId;
    private int subtitleId;
    private int quoteTextId;
    private int quoteSourceId;

    Page() {
        newPage();
    }

    public Page newPage() {
        this.bodyId = -1;
        this.subtitleId = -1;
        this.quoteTextId = -1;
        this.quoteSourceId = -1;
        return this;
    }
    public Page set_body(int bodyId) {
        this.bodyId = bodyId;
        return this;
    }
    public Page set_subtitle(int subtitleId) {
        this.subtitleId = subtitleId;
        return this;
    }
    public Page set_quote(int quoteTextId, int quoteSourceId) {
        this.quoteTextId = quoteTextId;
        this.quoteSourceId = quoteSourceId;
        return this;
    }
}