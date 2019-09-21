package com.sjhd.sdk.erospluginssocialsdk.model;

import java.util.List;

public class GoogleAccountDto {

    /**
     * account : {"name":"sjjrdfivk@gmail.com","type":"com.google"}
     * displayName : Q Rex
     * email : sjjrdfivk@gmail.com
     * expirationTimeSecs : 1569035090
     * expired : false
     * familyName : Rex
     * givenName : Q
     * grantedScopes : [{"scopeUri":"https://www.googleapis.com/auth/userinfo.profile"},{"scopeUri":"https://www.googleapis.com/auth/userinfo.email"},{"scopeUri":"openid"},{"scopeUri":"profile"},{"scopeUri":"email"}]
     * id : 104252545646555989031
     * idToken : eyJhbGciOiJSUzI1NiIsImtpZCI6IjBiMGJmMTg2NzQzNDcxYTFlZGNhYzMwNjBkMTI1NmY5ZTQwNTBiYTgiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiI1NDI1NTYwMzIwMzYtMnA5c3FqNTVjazI4aWV1c25vMzhlMHY1aDB1MXNlcWQuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiI1NDI1NTYwMzIwMzYtNjQ2anFpOTVxaGVlanVsYTl0OGIwazBicGFmMDFkdDIuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMDQyNTI1NDU2NDY1NTU5ODkwMzEiLCJlbWFpbCI6InNqanJkZml2a0BnbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwibmFtZSI6IlEgUmV4IiwicGljdHVyZSI6Imh0dHBzOi8vbGg2Lmdvb2dsZXVzZXJjb250ZW50LmNvbS8tS29OSXZkdWRBWVEvQUFBQUFBQUFBQUkvQUFBQUFBQUFBQUEvQUNIaTNyZVRBNFY1V0ROUEZfYTBQZ3pUU21UT3dhX3drZy9zOTYtYy9waG90by5qcGciLCJnaXZlbl9uYW1lIjoiUSIsImZhbWlseV9uYW1lIjoiUmV4IiwibG9jYWxlIjoiemgtQ04iLCJpYXQiOjE1NjkwMzE0OTAsImV4cCI6MTU2OTAzNTA5MH0.SMyMlZUwbAqeazhlV3u3rLHEDChPQIaxva5ypsuiMjsU-5kpNYbMKmFIlGy3I6rB6L23j3z97CGKhO079xHC06UgT-WuNAzR2AnkopNiqV7dAfUY5BZBjhRvVx_I0uqmMQ3HAcojkQ3pFy_OEWv-ePHPO3BaU2kD2F3zKsa16vLI698oLy5wY80AQPA9BWM4M3fw3i0w8KECpzUNclsDlG_CD8mNkGJnrvKVtOaYO4hcxeLu-G1H-cjBJnX6MyFYR0qmxbk6oPSUcmdCsv1CR1ZQ9AlUtZ1vZVJTTCotznpnxIXwGtu8ZwAj1LogSGydNeHQJU0PsXKiMuXMW_3nMQ
     * obfuscatedIdentifier : 24D665E2F836831582575DC587EABC8C
     * photoUrl : {"absolute":true,"authority":"lh6.googleusercontent.com","canonicalUri":{"$ref":"@"},"encodedAuthority":"lh6.googleusercontent.com","encodedPath":"/-KoNIvdudAYQ/AAAAAAAAAAI/AAAAAAAAAAA/ACHi3reTA4V5WDNPF_a0PgzTSmTOwa_wkg/s96-c/photo.jpg","encodedSchemeSpecificPart":"//lh6.googleusercontent.com/-KoNIvdudAYQ/AAAAAAAAAAI/AAAAAAAAAAA/ACHi3reTA4V5WDNPF_a0PgzTSmTOwa_wkg/s96-c/photo.jpg","hierarchical":true,"host":"lh6.googleusercontent.com","lastPathSegment":"photo.jpg","opaque":false,"path":"/-KoNIvdudAYQ/AAAAAAAAAAI/AAAAAAAAAAA/ACHi3reTA4V5WDNPF_a0PgzTSmTOwa_wkg/s96-c/photo.jpg","pathSegments":["-KoNIvdudAYQ","AAAAAAAAAAI","AAAAAAAAAAA","ACHi3reTA4V5WDNPF_a0PgzTSmTOwa_wkg","s96-c","photo.jpg"],"port":-1,"queryParameterNames":[],"relative":false,"scheme":"https","schemeSpecificPart":"//lh6.googleusercontent.com/-KoNIvdudAYQ/AAAAAAAAAAI/AAAAAAAAAAA/ACHi3reTA4V5WDNPF_a0PgzTSmTOwa_wkg/s96-c/photo.jpg"}
     * requestedScopes : [{"$ref":"$.grantedScopes[0]"},{"$ref":"$.grantedScopes[1]"},{"$ref":"$.grantedScopes[2]"},{"$ref":"$.grantedScopes[3]"},{"$ref":"$.grantedScopes[4]"}]
     */

    private AccountBean account;
    private String displayName;
    private String email;
    private int expirationTimeSecs;
    private boolean expired;
    private String familyName;
    private String givenName;
    private String id;
    private String idToken;
    private String obfuscatedIdentifier;
    private PhotoUrlBean photoUrl;
    private List<GrantedScopesBean> grantedScopes;
 //   private List<RequestedScopesBean> requestedScopes;
    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public AccountBean getAccount() {
        return account;
    }

    public void setAccount(AccountBean account) {
        this.account = account;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getExpirationTimeSecs() {
        return expirationTimeSecs;
    }

    public void setExpirationTimeSecs(int expirationTimeSecs) {
        this.expirationTimeSecs = expirationTimeSecs;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getObfuscatedIdentifier() {
        return obfuscatedIdentifier;
    }

    public void setObfuscatedIdentifier(String obfuscatedIdentifier) {
        this.obfuscatedIdentifier = obfuscatedIdentifier;
    }

    public PhotoUrlBean getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(PhotoUrlBean photoUrl) {
        this.photoUrl = photoUrl;
    }

    public List<GrantedScopesBean> getGrantedScopes() {
        return grantedScopes;
    }

    public void setGrantedScopes(List<GrantedScopesBean> grantedScopes) {
        this.grantedScopes = grantedScopes;
    }

//    public List<RequestedScopesBean> getRequestedScopes() {
//        return requestedScopes;
//    }
//
//    public void setRequestedScopes(List<RequestedScopesBean> requestedScopes) {
//        this.requestedScopes = requestedScopes;
//    }

    public static class AccountBean {
        /**
         * name : sjjrdfivk@gmail.com
         * type : com.google
         */

        private String name;
        private String type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class PhotoUrlBean {
        /**
         * absolute : true
         * authority : lh6.googleusercontent.com
         * canonicalUri : {"$ref":"@"}
         * encodedAuthority : lh6.googleusercontent.com
         * encodedPath : /-KoNIvdudAYQ/AAAAAAAAAAI/AAAAAAAAAAA/ACHi3reTA4V5WDNPF_a0PgzTSmTOwa_wkg/s96-c/photo.jpg
         * encodedSchemeSpecificPart : //lh6.googleusercontent.com/-KoNIvdudAYQ/AAAAAAAAAAI/AAAAAAAAAAA/ACHi3reTA4V5WDNPF_a0PgzTSmTOwa_wkg/s96-c/photo.jpg
         * hierarchical : true
         * host : lh6.googleusercontent.com
         * lastPathSegment : photo.jpg
         * opaque : false
         * path : /-KoNIvdudAYQ/AAAAAAAAAAI/AAAAAAAAAAA/ACHi3reTA4V5WDNPF_a0PgzTSmTOwa_wkg/s96-c/photo.jpg
         * pathSegments : ["-KoNIvdudAYQ","AAAAAAAAAAI","AAAAAAAAAAA","ACHi3reTA4V5WDNPF_a0PgzTSmTOwa_wkg","s96-c","photo.jpg"]
         * port : -1
         * queryParameterNames : []
         * relative : false
         * scheme : https
         * schemeSpecificPart : //lh6.googleusercontent.com/-KoNIvdudAYQ/AAAAAAAAAAI/AAAAAAAAAAA/ACHi3reTA4V5WDNPF_a0PgzTSmTOwa_wkg/s96-c/photo.jpg
         */

        private boolean absolute;
        private String authority;
       // private CanonicalUriBean canonicalUri;
        private String encodedAuthority;
        private String encodedPath;
        private String encodedSchemeSpecificPart;
        private boolean hierarchical;
        private String host;
        private String lastPathSegment;
        private boolean opaque;
        private String path;
        private int port;
        private boolean relative;
        private String scheme;
        private String schemeSpecificPart;
        private List<String> pathSegments;
        private List<?> queryParameterNames;

        public boolean isAbsolute() {
            return absolute;
        }

        public void setAbsolute(boolean absolute) {
            this.absolute = absolute;
        }

        public String getAuthority() {
            return authority;
        }

        public void setAuthority(String authority) {
            this.authority = authority;
        }

//        public CanonicalUriBean getCanonicalUri() {
//            return canonicalUri;
//        }
//
//        public void setCanonicalUri(CanonicalUriBean canonicalUri) {
//            this.canonicalUri = canonicalUri;
//        }

        public String getEncodedAuthority() {
            return encodedAuthority;
        }

        public void setEncodedAuthority(String encodedAuthority) {
            this.encodedAuthority = encodedAuthority;
        }

        public String getEncodedPath() {
            return encodedPath;
        }

        public void setEncodedPath(String encodedPath) {
            this.encodedPath = encodedPath;
        }

        public String getEncodedSchemeSpecificPart() {
            return encodedSchemeSpecificPart;
        }

        public void setEncodedSchemeSpecificPart(String encodedSchemeSpecificPart) {
            this.encodedSchemeSpecificPart = encodedSchemeSpecificPart;
        }

        public boolean isHierarchical() {
            return hierarchical;
        }

        public void setHierarchical(boolean hierarchical) {
            this.hierarchical = hierarchical;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getLastPathSegment() {
            return lastPathSegment;
        }

        public void setLastPathSegment(String lastPathSegment) {
            this.lastPathSegment = lastPathSegment;
        }

        public boolean isOpaque() {
            return opaque;
        }

        public void setOpaque(boolean opaque) {
            this.opaque = opaque;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public boolean isRelative() {
            return relative;
        }

        public void setRelative(boolean relative) {
            this.relative = relative;
        }

        public String getScheme() {
            return scheme;
        }

        public void setScheme(String scheme) {
            this.scheme = scheme;
        }

        public String getSchemeSpecificPart() {
            return schemeSpecificPart;
        }

        public void setSchemeSpecificPart(String schemeSpecificPart) {
            this.schemeSpecificPart = schemeSpecificPart;
        }

        public List<String> getPathSegments() {
            return pathSegments;
        }

        public void setPathSegments(List<String> pathSegments) {
            this.pathSegments = pathSegments;
        }

        public List<?> getQueryParameterNames() {
            return queryParameterNames;
        }

        public void setQueryParameterNames(List<?> queryParameterNames) {
            this.queryParameterNames = queryParameterNames;
        }

//        public static class CanonicalUriBean {
//            /**
//             * $ref : @
//             */
//
//            private String $ref;
//
//            public String get$ref() {
//                return $ref;
//            }
//
//            public void set$ref(String $ref) {
//                this.$ref = $ref;
//            }
//        }
    }

    public static class GrantedScopesBean {
        /**
         * scopeUri : https://www.googleapis.com/auth/userinfo.profile
         */

        private String scopeUri;

        public String getScopeUri() {
            return scopeUri;
        }

        public void setScopeUri(String scopeUri) {
            this.scopeUri = scopeUri;
        }
    }

//    public static class RequestedScopesBean {
//        /**
//         * $ref : $.grantedScopes[0]
//         */
//
//        private String $ref;
//
//        public String get$ref() {
//            return $ref;
//        }
//
//        public void set$ref(String $ref) {
//            this.$ref = $ref;
//        }
//    }
}
