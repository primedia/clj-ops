# clj-ops

Implements RentPath's standard ops routes.

## Usage

1. Add `[clj-ops "0.1.0"]` to the dependencies in your
`project.clj` file.
2. Generate the ops routes as follows:

```clj
(ns my-request-handler
  (:require [clj-ops.core :refer [ops-routes]]
            [clj-config :as c]
            [clojure.java.io :as io]))

(def build-info (read-string (slurp (io/resource "build.edn"))))

(def get-confusion-config
  []
  ;; do confusion lookup
  )

(defroutes
  (ops-routes build-info (delay (c/config)) get-confusion-config)
  (GET "/" [] "Hello World!"))
```

## License

Copyright © 2014 RentPath
