package com.example.android.ezonequiz;

/**
 * This is a container for holding information about each page.
 */
class Page {
    public int bodyId;
    public int subtitleId;
    public int quoteTextId;
    public int quoteSourceId;

    Page() {
        newPage();
    }

    public Page newPage() {
        this.bodyId = R.string.empty;
        this.subtitleId = R.string.empty;
        this.quoteTextId = R.string.empty;
        this.quoteSourceId = R.string.empty;
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