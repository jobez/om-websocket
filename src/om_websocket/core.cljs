(ns om-websocket.core
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
  (:require [cljs.reader :refer [read-string]]
            [goog.events :as events]
            [goog.dom :as gdom]
            goog.net.WebSocket
            [goog.debug :as debug]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :include-macros true]
            [clojure.string :as string]
            [cljs.core.async :as async :refer [chan put! pipe unique map< filter< alts! <!]]
            [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true])
  (:import [goog.ui IdGenerator]))

;; details that are context dependent:
;; 0. where the socket is connecting to
;; -> connection string can be passed in as a property in an opt
;; right now i am just going to implement the socket stuff
;; my familiar (probably stupid) way and then refactor for elegance

;; one chan for coordinating received data <- websocket
;; this channel is local to the component
;; another for coordinating outgoing data -> websocket
;; this channel is shared state to the app




(defcomponent om-websocket [data owner opts]
  (init-state [_]
              {:websocket (goog.net.WebSocket.)
               :->websocket (chan)
               })
  (will-mount [_]
              (let [websocket-> (om/get-shared owner :websocket->)
                    websocket (om/get-state owner :websocket)
                    ->websocket (om/get-state owner :->websocket)
                    in-coord (opts :in-coord)
                    out-coord (opts :out-coord)
                    ]
                (doto websocket
                  (.addEventListener goog.net.WebSocket.EventType.CLOSED #(print (debug/expose %)))
                  (.addEventListener goog.net.WebSocket.EventType.OPENED  #(print "the channel is alive"))
                  (.addEventListener goog.net.WebSocket.EventType.MESSAGE #(put! ->websocket (.-message %)))
                  (.open (opts :connection-str)))
                
                (in-coord ->websocket data owner)
                (out-coord websocket-> data owner websocket)))
  (render-state [_ _]
          (om/build (opts :child) data)))
