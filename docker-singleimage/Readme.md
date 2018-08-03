Run the entire taskmanager system: endpoint + scheduler + postgres in a single dockerimage.

No production usage, useful for testing

###Build & Run

* Build the `web` and `scheduler` modules jars. See the *Readme.md* on repo root.
* Copy both jar WITHOUT CHANGING THEIR NAMES in this folder
* run `#$ docker build -t taskmanager-all:test .`
* Run the dockerimage created forwarding port 8080 `#$ docker run -p 8080:8080 taskmanager-all:test`
