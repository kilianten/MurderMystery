[1mdiff --git a/src/main/java/display/DebugRenderer.java b/src/main/java/display/DebugRenderer.java[m
[1mindex 8ea7b9e..3a56ff7 100644[m
[1m--- a/src/main/java/display/DebugRenderer.java[m
[1m+++ b/src/main/java/display/DebugRenderer.java[m
[36m@@ -15,7 +15,7 @@[m [mpublic class DebugRenderer {[m
 [m
         Camera camera = state.getCamera();[m
         state.getGameObjectsOfClass(NPC.class).stream()[m
[31m-                .filter(gameObject -> !state.getCurrentLocation().equals(gameObject.getLocation()))[m
[32m+[m[32m                .filter(gameObject -> state.getCurrentLocationName().equals(gameObject.getLocation()))[m
                 .filter(gameObject -> camera.isInView(gameObject))[m
                 .forEach(npc ->[m
                 {   UIText nameText = new UIText(npc.getFirstName(), 30, 25, false);[m
[1mdiff --git a/src/main/java/state/State.java b/src/main/java/state/State.java[m
[1mindex 65a7cbd..fcedc9b 100644[m
[1m--- a/src/main/java/state/State.java[m
[1m+++ b/src/main/java/state/State.java[m
[36m@@ -276,6 +276,10 @@[m [mpublic abstract class State {[m
         return locations.get(currentLocation);[m
     }[m
 [m
[32m+[m[32m    public String getCurrentLocationName(){[m
[32m+[m[32m        return currentLocation;[m
[32m+[m[32m    }[m
[32m+[m
     public Location getLocation(String location){[m
         return locations.get(location);[m
     }[m
[1mdiff --git a/src/main/java/state/menu/ui/UIMainMenu.java b/src/main/java/state/menu/ui/UIMainMenu.java[m
[1mindex eb68126..48b5193 100644[m
[1m--- a/src/main/java/state/menu/ui/UIMainMenu.java[m
[1m+++ b/src/main/java/state/menu/ui/UIMainMenu.java[m
[36m@@ -26,7 +26,7 @@[m [mpublic class UIMainMenu extends VerticalContainer {[m
 [m
         addUIComponent(new UIText(Game.GAME_TITLE, 35, 30, true));[m
         addUIComponent(new UIButton("Play", this::loadMap));[m
[31m-        addUIComponent(new UIButton("Level Edit",  (state) -> state.setNextState(new EditorState(windowSize, state.getInput(), state.getSettings(), new Size(40, 40)))));[m
[32m+[m[32m        addUIComponent(new UIButton("Level Edit",  (state) -> state.setNextState(new EditorState(windowSize, state.getInput(), state.getSettings(), new Size(150, 150)))));[m
         addUIComponent(new UIButton("Options", (state) -> ((MenuState) state).enterMenu(new UIOptionMenu(windowSize, state.getSettings()))));[m
         addUIComponent(new UIButton("Exit", (state) -> System.exit(0)));[m
     }[m
