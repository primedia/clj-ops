# clj-ops

Implements RentPath's standard ops routes.

# Breaking changes (November 2014, 1.0.0)

 As of 1.0.0, ops-routes takes 3 functions as input.

## Usage

1. Add `[clj-ops "1.0.0"]` to the dependencies in your
`project.clj` file.
2. Generate the ops routes as follows:

```clj
(ns my-request-handler
  (:require [clj-ops.core :refer [ops-routes]]
            [clj-config :as c]
            [clojure.java.io :as io]))

(defn build-info 
  []
  (read-string (slurp (io/resource "build.edn"))))

(def get-confusion-config
  []
  ;; do confusion lookup
  )

(defroutes
  (GET "/" [] "Hello World!")

                           ; functions
  (ops-routes {:build-info build-info
               :env        c/config
               :config     get-confusion-config}))
```

## License

Copyright © 2014 RentPath
