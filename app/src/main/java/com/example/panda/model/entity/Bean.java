package com.example.panda.model.entity;

import java.util.List;

/**
 * Created by lenovo on 2017/8/23.
 */

public class Bean {

    private List<TabBean> tab;

    public List<TabBean> getTab() {
        return tab;
    }

    public void setTab(List<TabBean> tab) {
        this.tab = tab;
    }

    public static class TabBean {
        /**
         * image : http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2015/12/15/1450172469187_603.jpg
         * noimage :
         * title : 首页
         * url : http://www.ipanda.com/kehuduan/PAGE14501749764071042/index.json
         */

        private String image;
        private String noimage;
        private String title;
        private String url;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getNoimage() {
            return noimage;
        }

        public void setNoimage(String noimage) {
            this.noimage = noimage;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
