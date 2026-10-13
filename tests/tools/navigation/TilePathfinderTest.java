package tools.navigation;

public final class TilePathfinderTest {
    private static void check(boolean ok, String info) {
        if (!ok) throw new AssertionError(info);
    }
    private static int x(short s) { return ((s & 65535) >> 8) & 255; }
    private static int y(short s) { return s & 255; }
    private static int[] map(int w,int h) {return new int[w*h];}
    public static void main(String[] args) {
        TilePathfinder finder = TilePathfinder.shared();
        int[] tiles = map(11,11);
        for (int y=1;y<=9;y++) tiles[y*11+5]=1;
        tiles[7*11+5]=0; // single gap
        TilePathfinder.Route r = finder.route(tiles,11,11,2,2,8,2,40,0);
        check(r!=null && r.complete, "full route exists");
        check(x(r.steps[0])==2 && y(r.steps[0])==2,"forward from source");
        check(x(r.steps[r.steps.length-1])==8 &&
              y(r.steps[r.steps.length-1])==2,"arrive at target");
        for(int i=1;i<r.steps.length;i++){
            int dx=Math.abs(x(r.steps[i])-x(r.steps[i-1]));
            int dy=Math.abs(y(r.steps[i])-y(r.steps[i-1]));
            check(dx+dy==1,"never jump or cross walls");
            check(tiles[y(r.steps[i])*11+x(r.steps[i])]!=1,"never walk on wall");
        }
        r=finder.route(tiles,11,11,2,2,8,2,4,0);
        check(r!=null && !r.complete && r.steps.length==4, "path chunk");
        for (int y=0;y<11;y++) tiles[y*11+5]=1;
        check(finder.route(tiles,11,11,2,2,8,2,40,0)==null,
             "reject unreachable target");
        int[] sample=map(6,6);
        sample[4*6+4]=1;
        r=finder.route(sample,6,6,1,1,4,4,45,1);
        check(r!=null && r.complete && r.steps.length>1,"approach blocked gate");
        check(finder.route(sample,6,6,1,1,4,4,45,0)==null,"blocked exact point");
        check(finder.route(sample,128,128,1,1,4,4,45,0)==null,"reject byte overflow");
        System.out.println("TilePathfinderTest: ALL PASS");
    }
}
