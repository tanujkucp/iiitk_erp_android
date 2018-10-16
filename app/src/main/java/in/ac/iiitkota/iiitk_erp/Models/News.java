package in.ac.iiitkota.iiitk_erp.Models;

public class News {

    /**
     * content : {"title":"Invitation for Nomination of Vayoshrestha Samman 2018","text":"Invitation of nominations for National Award for Senior Citizens (Vayoshreshtha Samman) 2018 to eminent senior citizens and institutions for distinguished service for the cause of elderly persons."}
     * _id : 5b14f89a3ef6f349769c77a8
     * type : others
     * posted_by : rky186GDz
     * file : file-1528101044764.pdf
     * uploadTitle : Download the Invitation here
     * create_time : 2018-06-04T08:30:18.699Z
     * __v : 0
     */
    //at a time only one of will be peresent in data
            // file OR
            //link + linkheader

    private Content content;
    private String _id;
    private String type;
    private String posted_by;
    private String file,link,linkheader;
    private String uploadTitle;
    private String create_time;
    private int __v;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPosted_by() {
        return posted_by;
    }

    public void setPosted_by(String posted_by) {
        this.posted_by = posted_by;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getUploadTitle() {
        return uploadTitle;
    }

    public void setUploadTitle(String uploadTitle) {
        this.uploadTitle = uploadTitle;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLinkheader() {
        return linkheader;
    }

    public void setLinkheader(String linkheader) {
        this.linkheader = linkheader;
    }

    public static class Content {
        /**
         * title : Invitation for Nomination of Vayoshrestha Samman 2018
         * text : Invitation of nominations for National Award for Senior Citizens (Vayoshreshtha Samman) 2018 to eminent senior citizens and institutions for distinguished service for the cause of elderly persons.
         */

        private String title;
        private String text;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
