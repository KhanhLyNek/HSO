package tools.navigation;

public final class WorldMemoryTest {
    private static void check(boolean v, String text) {
        if(!v)throw new AssertionError(text);
    }
    public static void main(String[] args) {
        WorldMemory g=new WorldMemory();
        g.visit(1,"Làng");
        g.visit(2,"Đường rừng");
        g.visit(3,"Hang Động");
        check(g.nextMap(1,3)==-1,"cannot guess world links");
        g.connect(1,2,144,200);
        g.connect(2,3,400,200);
        check(g.nextMap(1,3)==2,"first map in shortest route");
        check(g.nextMap(2,3)==3,"direct");
        check(g.nextMap(3,1)==-1,"directed transitions need learning");
        check(g.edgeX(1,2)==144 && g.edgeY(2,3)==200,"gateway coord");
        g.observeMonsters(3,55,5);
        check(g.bestKnownLevelMap(1,54)==3,"level relevant known world");
        check(g.bestKnownLevelMap(1,15)==-1,"no inappropriate level world");
        g.visit(3,"Hang Động");
        check(g.visitsByName("hang động")==2,"remember explored destination");
        g.clear();
        check(g.knownMaps()==0 && g.knownEdges()==0,"reset memory");
        System.out.println("WorldMemoryTest: ALL PASS");
    }
}
