package com.example.panda.model.entity.home;

import java.util.List;

/**
 * Created by XXASUS on 2017/8/29.
 */

public class MarvellousBean {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }
//http://115.182.9.189/api/getVideoInfoForCBox.do?pid=26bb0b9e822a42f9959aa450cfa2fc47
    public static class ListBean {
        /**
         * url : http://live.ipanda.com/2017/08/27/VIDElG9n8wL27GLG9tKV29TA170827.shtml
         * image : http://p1.img.cctvpic.com/photoworkspace/2017/08/27/2017082713093012665.jpg
         * title : 小胖子，胖到没脖子
         * videoLength : 00:18
         * id : TITE1503901857878204
         * daytime : 2017-08-28
         * type : 2
         * pid : 26bb0b9e822a42f9959aa450cfa2fc47
         * vid :
         * order : 1
         */

        private String url;
        private String image;
        private String title;
        private String videoLength;
        private String id;
        private String daytime;
        private String type;
        private String pid;
        private String vid;
        private String order;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getVideoLength() {
            return videoLength;
        }

        public void setVideoLength(String videoLength) {
            this.videoLength = videoLength;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDaytime() {
            return daytime;
        }

        public void setDaytime(String daytime) {
            this.daytime = daytime;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getVid() {
            return vid;
        }

        public void setVid(String vid) {
            this.vid = vid;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }
    }
}
