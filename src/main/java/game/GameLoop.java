package game;

public class GameLoop implements Runnable {

    private Game game;
    public static final int UPDATES_PER_SECOND = 60;

    private boolean running;
    private final double updateRate = 1.0d/UPDATES_PER_SECOND;

    private long nextStatTime;
    private int fps, ups;

    public GameLoop(Game game){
        this.game = game;
    }

    @Override
    public void run() {
        running = true;
        double accumulator = 0;
        long currentTime, lastUpdate = System.currentTimeMillis();
        nextStatTime = System.currentTimeMillis() + 1000;

        while (running){
            currentTime = System.currentTimeMillis();
            double lastRenderTimeSeconds = (currentTime - lastUpdate) / 1000d;
            accumulator += lastRenderTimeSeconds;
            lastUpdate = currentTime;

            if(accumulator >= updateRate){
                while(accumulator > updateRate){
                    update();
                    accumulator -= updateRate;
                }
                render();
            }
            printStats();
        }
    }

    private void printStats() {
        if(System.currentTimeMillis() > nextStatTime){
            System.out.println("FPS: " + fps + " UPS: " + ups);
            fps = 0;
            ups = 0;
            nextStatTime = System.currentTimeMillis() + 1000;
        }
    }

    private void render(){
        game.render();
        fps++;
    }

    private void update(){
        game.update();
        ups++;
    }
}
