package by.tc.home05.multithreading1.multiplication;

public class MultiplicationMatrices {

    private int[][] firstMatrix;
    private int[][] secondMatrix;
    private int[][] finalMatrix;
    private int actualRow; 
    private int endThreads;
    private int countThreads;
    private final Object objSynchr = new Object();

    public MultiplicationMatrices(int[][] firstMatrix, int[][] secondMatrix) {
        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
        countThreads = firstMatrix.length / 3;
    }

    public MultiplicationMatrices(int[][] firstMatrix, int[][] secondMatrix, int countThreads) {
        this(firstMatrix, secondMatrix);
        this.countThreads = countThreads;
    }

    public int[][] matrMultiplication() throws Exception {
        actualRow = -1;
        endThreads = 0;
        boolean tmp = compilingMatrices();
        if (!tmp) {
            throw new Exception("Матрицы не могут быть перемножены!");
        } else {
            finalMatrix = new int[firstMatrix.length][firstMatrix[0].length];
            for (int i = 0; i < countThreads; i++) {
                multiplyingThread();
            }
        }
        while (!allThreadsEnd());
        return finalMatrix;
    }

    private void multiplyingThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean flag = true;
                while (flag) {
                    try {
                        int tmp;
                        tmp = contrThreads();
                        multiplyRowOnColumns(tmp);
                    } catch (Exception e) {
                        flag = false;
                    }
                }
                setThreadEnd();
            }
        }).start();
    }
    
    private int contrThreads() throws Exception {
        int res;
        synchronized (objSynchr) {
            actualRow++;
            if (actualRow < firstMatrix.length) {
                res = actualRow;
            } else {
                throw new Exception();
            }
        }
        return res;
    }

    private boolean compilingMatrices() {
        boolean res;
        if (firstMatrix == null || secondMatrix == null) {
            return false;
        }
        res = firstMatrix[0].length == secondMatrix.length;
        return res;
    }

    private void multiplyRowOnColumns(int indexRow) {
        int length = firstMatrix[indexRow].length;
        int sum;
        for (int i = 0; i < length; i++) {
            sum = 0;
            for (int j = 0; j < length; j++) {
                sum += firstMatrix[indexRow][j] * secondMatrix[j][i];
            }
            this.finalMatrix[indexRow][i] = sum;
        }
    }
    
    private void setThreadEnd() {
        synchronized (objSynchr) {
            endThreads++;
        }
    }
    
    private boolean allThreadsEnd() {
        synchronized (objSynchr) {
            return endThreads == countThreads;
        }
    }


}

