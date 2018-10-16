package in.ac.iiitkota.iiitk_erp.Models;

public class Event {

    /**
     * _id : r1wNOEuCM
     * title : IIIT Kota Celebrated Yoga Day
     * description : IIIT Kota celebrated International Yoga Day on June 21, 2017 in MNIT Jaipur Campus. How to avoid stress in present day circumstances and stress management in general were discussed.
     * date : 2018-04-30T18:30:00.000Z
     * type : cultural
     * organizer : IIIT Kota
     * eventPhoto : file-1526380588280.jpg
     * create_time : 2018-05-15T10:36:31.129Z
     * __v : 0
     */

    private String _id;
    private String title;
    private String description;
    private String date;
    private String type;
    private String organizer;
    private String eventPhoto;
    private String create_time;
    private int __v;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getEventPhoto() {
        return eventPhoto;
    }

    public void setEventPhoto(String eventPhoto) {
        this.eventPhoto = eventPhoto;
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
}
