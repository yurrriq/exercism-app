(ns exercism.core
  (:require-macros [natal-shell.core :refer [with-error-view]]
                   [natal-shell.components :refer [view text image
                                                   tab-bar-ios tab-bar-ios-item
                                                   touchable-highlight]]
                   [natal-shell.linking-ios :as linking-ios]
                   [natal-shell.alert :refer [alert]])
  (:require [om.next :as om :refer-macros [defui]]))

(enable-console-print!)

(set! js/React (js/require "react-native/Libraries/react-native/react-native.js"))

(defonce app-state (atom {:app/msg          "exercism.app"
                          :app/selected-tab :welcome}))

(defn- switch-to-tab [this item]
  #(om/transact! this `[(tab-bar/select {:item ~item})]))

(defui HelloWorld
  Object
  (render [this]
    (view
      {:style {:flexDirection "column" :margin 40 :alignItems "center"}}
      (text
        {:style {:fontSize     50
                 :fontWeight   "100"
                 :marginBottom 20
                 :textAlign    "center"}}
        "Hello, world!"))))

(def hello (om/factory HelloWorld))

(defui WelcomeView
  Object
  (render [this]
    (let [msg (om/props this)]
      (view
        {:style {:flexDirection "column" :margin 40 :alignItems "center"}}
        (text
          {:style {:fontSize     50
                   :fontWeight   "100"
                   :marginBottom 20
                   :textAlign    "center"}}
          msg)

        (image
          {:source {:uri "https://raw.githubusercontent.com/exercism/exercism.io/master/public/img/e_red.png"}
           :style  {:width 80 :height 80 :marginBottom 30}}
          )

        (touchable-highlight
          {:style   {:backgroundColor "#999" :padding 10 :borderRadius 5}
           :onPress #(linking-ios/open-url "http://exercism.io")}
          ;; #(linking-ios/open-url "http://exercism.io")
          (text
            {:style {:color "white" :textAlign "center" :fontWeight "bold"}}
            "exercism.io"))))))

(def welcome-view (om/factory WelcomeView))


(def e-grey "data:image/png;base64,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")

(defui MainView
  static om/IQuery
  (query [this]
    '[:app/msg :app/selected-tab])

  Object
  (render [this]
    (with-error-view
      (let [{:keys [app/selected-tab app/msg]} (om/props this)]
        (tab-bar-ios
         {:selectedTab selected-tab}
         (let [item :welcome]
           (tab-bar-ios-item
            {:selected   (= selected-tab item)
             ;; :icon       "https://raw.githubusercontent.com/exercism/exercism.io/master/public/img/e.png"
             :icon {:uri e-grey :scale 15}
             :title "Exercises"
             ;; :systemIcon "most-recent"
             :badge      42
             :onPress    (switch-to-tab this item)}
            (welcome-view msg)))
         (let [item :hello-world]
           (tab-bar-ios-item
            {:selected   (= selected-tab item)
             :systemIcon :most-recent
             :onPress    (switch-to-tab this item)}
            (hello))))))))


(defmulti read om/dispatch)

(defmethod read :default
  [{:keys [state]} k _]
  (let [st @state]
    (if-let [[_ v] (find st k)]
      {:value v}
      {:value :not-found})))

(defmulti mutate om/dispatch)

(defmethod mutate 'tab-bar/select
  [{:keys [state]} _ {:keys [item]}]
  {:action #(swap! state assoc :app/selected-tab item)})

(def reconciler
  (om/reconciler
   {:state        app-state
    :parser       (om/parser {:read read :mutate mutate})
    :root-render  #(.render js/React %1 %2)
    :root-unmount #(.unmountComponentAtNode js/React %)}))

(om/add-root! reconciler MainView 1)
