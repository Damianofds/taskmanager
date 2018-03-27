package it.fds.taskmanager;

public enum Endpoints {

        TASK("/task"),
        LIST("/list"),
        GET_UUID("/{uuid}"),
        CREATE("/create"),
        UPDATE_UUID("/{uuid}"),
        POSTPONE("/{uuid}/postpone"),
        RESOLVE("/{uuid}/resolve"),
        NOTIFY("/notify");


        String url;

    Endpoints (String endpointUrl) {
            this.url = endpointUrl;
        }

        public String getUrl() {
            return url;
        }
    }

