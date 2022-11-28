import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args)
    {

        String folderPath = "data/Метро Москвы.html";
        File file = new File(folderPath);
        Node root = new Node(file);

        //TODO: подсчет объема папки через ForkJoinPool
        FolderSizeCalculator folderSizeCalculator = new FolderSizeCalculator(root);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(folderSizeCalculator);
        System.out.println(root.getSize());

    }

    // TODO: подсчет объема папки обычным методом в 1 поток
    public static long getFolderSize(File folder)
    {
        if(folder.isFile()){
            return folder.length();
        }
        long sum = 0;
        File[] files = folder.listFiles();
        for(File file : files) sum +=getFolderSize(file);

        return sum;
    }
}
