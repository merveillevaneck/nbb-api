(ns app
  (:require [nbb.core :as nbb]
            ["shelljs$default" :as sh]
            ["express$default" :as ex]))

(def *server (atom nil))

(def app (ex))
(def port js/process.env.PORT)

(defn handler [req, res, next] (.send res "hello world"))


(defn serve-if-not-exists []
  (if (nil? @*server)
    (reset! *server (.listen app))
    (println (concat "Server already running on port " port))))

(defn create-app []
  (let [app (ex)]
    (.get app "/" handler)
    (serve-if-not-exists)))

(defn stop-if-exists []
  (if (not (nil? @*server))
    (do (.close @*server)
        (reset! *server nil))
    (println (concat "Server on port " port " already closed"))))

(create-app) ;; <== evaluate this in your editor repl to start the server
(println @*server) ;; <== evaluate this to check if the server is started
(stop-if-exists) ;; <== evaluate this to close the server if its defined



