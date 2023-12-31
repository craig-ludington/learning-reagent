(ns ^:figwheel-hooks me.alpheus.paloma-negra
  (:require
   [goog.dom :as gdom]
   [reagent.core :as reagent :refer [atom]]
   [reagent.dom :as rdom]))

(defonce app-state (atom {:text "Paloma Negra"}))

(defn get-app-element []
  (gdom/getElement "app"))

(defn logo []
  [:img {:style {:position "absolute" :top 0 :left 0 :border 0}
         :alt "Paloma Negra"
         :src "paloma-negra-600-351.png"}])

(defn images []
  [:div
   (for [x (range 4)]
     [:div {:id (str "foo-" x)}             :onmouseover #(js/alert (str "foo-" x))
      [:img {:style {:position "absolute" :top 400 :left (* x 132) :border 0}
             :alt "Paloma Negra"
             :src "paloma-negra-225-132.png"}]])])

(defn home-page []
  [:div
   [logo]
   [images]])

(defn mount [el]
  (rdom/render [home-page] el))

(defn mount-app-element []
  (when-let [el (get-app-element)]
    (mount el)))

;; conditionally start your application based on the presence of an "app" element
;; this is particularly helpful for testing this ns without launching the app
(mount-app-element)

;; specify reload hook with ^:after-load metadata
(defn ^:after-load on-reload []
  (mount-app-element)
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
