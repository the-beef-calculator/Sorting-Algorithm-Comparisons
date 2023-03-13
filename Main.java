

        import java.util.*;
        import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        List<Integer> numberList = new ArrayList<>();

        System.out.println("Enter size of Array (0 for random size)");
        int input = 0;
        try
        {
            input = scan.nextInt();
        }
        catch(InputMismatchException ime)
        {
            System.out.println("Enter an integer only.");
            System.exit(1);
        }

        System.out.println("Populating array with random values...");

        if (input == 0)
        {
            for (int i = 0; i < rand.nextInt(100000);  i++) {
                numberList.add(rand.nextInt(1000000));
            }

        }
        else
        {
            for (int i = 0; i < input;  i++) {
                numberList.add(rand.nextInt(1000000));
            }

        }

        System.out.println("Array populated!");

        System.out.println("Include bogo sort? (Only enable if size of array is less than 10, or you'll be waiting a while.) (YES/NO)");

        if (scan.nextLine().equalsIgnoreCase("yes") || scan.nextLine().equalsIgnoreCase("y"))
        {
            BogoSort(numberList);
            Collections.shuffle(numberList);
        }

        selectionSort(numberList);
        Collections.shuffle(numberList);

        bubbleSort(numberList);
        Collections.shuffle(numberList);

        long startTime = System.nanoTime();
        int iterations = mergeSort(numberList, 0);
        long endTime = System.nanoTime();
        long executionTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

        System.out.println("Merge Sort execution time: " + executionTime + "ms.");
        Collections.shuffle(numberList);

        startTime = System.nanoTime();
        quickSort(numberList,0,numberList.size()-1);
        endTime = System.nanoTime();
        executionTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

        System.out.println("Quick Sort execution time: " + executionTime + "ms.");


    }
    public static boolean isSorted(List<Integer> list)
    {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < list.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static void BogoSort(List<Integer> list)
    {
        long startTime = System.nanoTime();
        int attempts = 0;
        while(!isSorted(list))
        {
            Collections.shuffle(list);
            attempts++;
        }
        long endTime = System.nanoTime();
        long executionTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

        System.out.println("Bogo Sort execution time: " + executionTime + " ms with " + attempts + " amount of shuffles. ");


    }

    public static void selectionSort(List<Integer> list)
    {
        long startTime = System.nanoTime();

        int swaps = 0;
        int n = list.size();
        for (int i = 0; i < n - 1; i++)
        {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (list.get(j) < list.get(minIndex)) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = list.get(i);
                list.set(i, list.get(minIndex));
                list.set(minIndex, temp);
                swaps++;

            }
        }
        long endTime = System.nanoTime();
        long executionTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

        System.out.println("Selection Sort execution time: "+ executionTime + "ms with " + swaps + " swaps.");

    }

    public static void bubbleSort(List<Integer> list)
    {
        long startTime = System.nanoTime();
        long swaps = 0;
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    int temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swaps++;
                }
            }
        }

        long endTime = System.nanoTime();
        long executionTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

        System.out.println("Bubble Sort execution time: " + executionTime + "ms with " + swaps + " swaps.");

    }

    public static int mergeSort(List<Integer> list, int iterations) {
        iterations++;

        if (list.size() <= 1) {
            return iterations;
        }
        int mid = list.size() / 2;
        List<Integer> left = new ArrayList<>(list.subList(0, mid));
        List<Integer> right = new ArrayList<>(list.subList(mid, list.size()));
        mergeSort(left, iterations);
        mergeSort(right, iterations);
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i) < right.get(j)) {
                list.set(k++, left.get(i++));
            } else {
                list.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) {
            list.set(k++, left.get(i++));
        }
        while (j < right.size()) {
            list.set(k++, right.get(j++));
        }

        return iterations;

    }

    public static void quickSort(List<Integer> arr, int low, int high) {


        if (low < high)
        {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }



    }
    public static int partition(List<Integer> arr, int low, int high) {
        int pivot = arr.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr.get(j) < pivot) {
                i++;
                int temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
            }
        }
        int temp = arr.get(i + 1);
        arr.set(i + 1, arr.get(high));
        arr.set(high, temp);
        return i + 1;
    }




}

