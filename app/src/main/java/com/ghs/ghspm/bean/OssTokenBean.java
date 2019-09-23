package com.ghs.ghspm.bean;

/**
 * Author:wang_sir
 * Time:2018/10/9 16:38
 * Description:This is OssTokenBean
 */
public class OssTokenBean {

    /**
     * code : 1000
     * message : 成功
     * data : {"SecurityToken":"CAISmwJ1q6Ft5B2yfSjIr4iCPI/appJ7xbGYO0j7qGI/OMtE2KbMtzz2IH1LfXNgBesYtvkxlWlY6/0clrh+W4NIX0rNaY5t9ZlN9wqkbtIcdWUcYPhW5qe+EE2/VjTJvqaLEdibIfrZfvCyESem8gZ43br9cxi7QlWhKufnoJV7b9MRLGbaAD1dH4UUXEgAzvUXLnzML/2gHwf3i27LdipStxF7lHl05NbYoKiV4QGMi0bhmK1H5dazAOD9NpAwbc0nAo3rhbMqKvub6kMKtUgWrpURpbdf5DLKsuuaB1Rs+BicO4LWiIYyc1YjNvRhRfYZ8qmlxaAj5PagnoD22gtLOvpOTyPcSYavzc3JAuq1McwjcrL2K4AfPTZbngycGoABljJ7wpHOvwCGhxhAhaznMTI1DnhlrmpKup80X9Dmgb6CcTaqtsz72lMI/x6xel3vL/zRfdCTgbdw8Y+ayRGEbGvvwT3lo2e7CNdpHMz5iiBSh3DvYXoMgOrIH+zU36kTaUA/IYbDZpnHoN7IR89uf6L1QV2S71iQBF48k4OufJk=","RequestId":"2E74B8FB-765D-412A-A535-D0148CC4E28E","AccessKeyId":"STS.NK7w5nKMZrss9nJLbj4Dh7cgU","AccessKeySecret":"5mFUuCAEDFXsS4naveLLoYKVvSvBmGHA4AC3ryDjZY6u","Expiration":"2018-10-09T09:37:34Z"}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * SecurityToken : CAISmwJ1q6Ft5B2yfSjIr4iCPI/appJ7xbGYO0j7qGI/OMtE2KbMtzz2IH1LfXNgBesYtvkxlWlY6/0clrh+W4NIX0rNaY5t9ZlN9wqkbtIcdWUcYPhW5qe+EE2/VjTJvqaLEdibIfrZfvCyESem8gZ43br9cxi7QlWhKufnoJV7b9MRLGbaAD1dH4UUXEgAzvUXLnzML/2gHwf3i27LdipStxF7lHl05NbYoKiV4QGMi0bhmK1H5dazAOD9NpAwbc0nAo3rhbMqKvub6kMKtUgWrpURpbdf5DLKsuuaB1Rs+BicO4LWiIYyc1YjNvRhRfYZ8qmlxaAj5PagnoD22gtLOvpOTyPcSYavzc3JAuq1McwjcrL2K4AfPTZbngycGoABljJ7wpHOvwCGhxhAhaznMTI1DnhlrmpKup80X9Dmgb6CcTaqtsz72lMI/x6xel3vL/zRfdCTgbdw8Y+ayRGEbGvvwT3lo2e7CNdpHMz5iiBSh3DvYXoMgOrIH+zU36kTaUA/IYbDZpnHoN7IR89uf6L1QV2S71iQBF48k4OufJk=
         * RequestId : 2E74B8FB-765D-412A-A535-D0148CC4E28E
         * AccessKeyId : STS.NK7w5nKMZrss9nJLbj4Dh7cgU
         * AccessKeySecret : 5mFUuCAEDFXsS4naveLLoYKVvSvBmGHA4AC3ryDjZY6u
         * Expiration : 2018-10-09T09:37:34Z
         */

        private String SecurityToken;
        private String RequestId;
        private String AccessKeyId;
        private String AccessKeySecret;
        private String Expiration;

        public String getSecurityToken() {
            return SecurityToken;
        }

        public void setSecurityToken(String SecurityToken) {
            this.SecurityToken = SecurityToken;
        }

        public String getRequestId() {
            return RequestId;
        }

        public void setRequestId(String RequestId) {
            this.RequestId = RequestId;
        }

        public String getAccessKeyId() {
            return AccessKeyId;
        }

        public void setAccessKeyId(String AccessKeyId) {
            this.AccessKeyId = AccessKeyId;
        }

        public String getAccessKeySecret() {
            return AccessKeySecret;
        }

        public void setAccessKeySecret(String AccessKeySecret) {
            this.AccessKeySecret = AccessKeySecret;
        }

        public String getExpiration() {
            return Expiration;
        }

        public void setExpiration(String Expiration) {
            this.Expiration = Expiration;
        }
    }
}
