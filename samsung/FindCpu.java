import java.util.Scanner;

/* sample input
3
2
2 5
3 3
3
1 3
3 3
7 5
2
7 9
9 1
*/

public class FindCpu
{

    static int MAX_CPU_REQUIREMENT = 5;
    static int MAX_RUNTIME_REQUIREMEN = 10;

    static final QueueProcessing[] differentArray = new QueueProcessing[5];

    public static void main(final String[] args)
    {
        // TODO Auto-generated method stub
        for (int index = 0; index < 5; index++)
        {
            differentArray[index] = new QueueProcessing();
        }

        final Scanner scan = new Scanner(System.in);
        final int testCases = scan.nextInt();
        for (int index = 1; index <= testCases; index++)
        {
            final int testInputSize = scan.nextInt();
            final ProcessingUnit[] processing = new ProcessingUnit[testInputSize];
            for (int arrayInput = 0; arrayInput < testInputSize; arrayInput++)
            {
                processing[arrayInput] = new ProcessingUnit(scan.nextInt(), scan.nextInt());
            }
            final int cpuRequirement = minCpuEvaluation(processing);
            System.out.println("#" + index + " " + cpuRequirement);
        }
        scan.close();
    }

    static int minCpuEvaluation(final ProcessingUnit[] processing)
    {
        int cpuRequirement = 1;
        final int maxTimeComplition = Integer.MAX_VALUE;
        final int minCpuRequirement = Integer.MAX_VALUE;
        int insertionCount = 1;
        for (;;)
        {
            if (cpuRequirement == 5)
            {
                return -1;
            }
            for (int index = 0; index < cpuRequirement; index++)
            {
                differentArray[index].reset();
            }
            insertionCount = 0;
            for (final ProcessingUnit pu : processing)
            {

                for (int index = 0; index < cpuRequirement; index++)
                {
                    if (differentArray[index].checkInserstion(pu) <= MAX_RUNTIME_REQUIREMEN)
                    {
                        differentArray[index].processInThisQueue(pu);
                        insertionCount++;
                        break;
                    }
                }

            }
            if (insertionCount == processing.length)
            {
                return cpuRequirement;
            }
            cpuRequirement++;
        }

    }

}

class QueueProcessing
{
    private int timeElapsed;
    private int timeStamp;
    private boolean firstItem = true;

    /**
     * @return the itemProcessed
     */


    /**
     * @return the timeStamp
     */
    public int getTimeStamp()
    {
        return timeStamp;
    }

    /**
     * @param timeStamp the timeStamp to set
     */
    public void setTimeStamp(final int timeStamp)
    {
        this.timeStamp = timeStamp;
    }

    /**
     * @return the timeElapsed
     */
    public int getTimeElapsed()
    {
        return timeElapsed;
    }

    /**
     * @param timeElapsed the timeElapsed to set
     */
    public void setTimeElapsed(final int timeElapsed)
    {
        this.timeElapsed = timeElapsed;
    }

    /**
     * @return the timeToWait
     */


    int checkInserstion(final ProcessingUnit pu)
    {
        final int tempTimeElapsed = this.getTimeElapsed() + pu.getTimeUnit() + (this.getTimeStamp() - pu.getTimeStamp());
        return tempTimeElapsed;
    }

    void processInThisQueue(final ProcessingUnit pu)
    {

        final int tempTimeElapsed = this.getTimeElapsed() + pu.getTimeUnit() + (this.getTimeStamp() - pu.getTimeStamp());
        this.setTimeElapsed(tempTimeElapsed);
        if (this.isFirstItem())
        {
            this.setTimeStamp(pu.getTimeUnit() + pu.getTimeStamp());
            this.setFirstItem(false);
        }
        else
        {
            this.setTimeStamp(this.getTimeStamp() + pu.getTimeUnit());
        }

    }

    /**
     * @return the firstItem
     */
    public boolean isFirstItem()
    {
        return firstItem;
    }

    /**
     * @param firstItem the firstItem to set
     */
    public void setFirstItem(final boolean firstItem)
    {
        this.firstItem = firstItem;
    }

    public void reset()
    {
        this.setFirstItem(true);
        this.setTimeElapsed(0);
        this.setTimeStamp(0);
    }
}

class ProcessingUnit
{
    private int timeStamp;
    private int timeUnit;

    ProcessingUnit(final int ts, final int tu)
    {
        timeStamp = ts;
        timeUnit = tu;
    }

    public int getTimeStamp()
    {
        return timeStamp;
    }

    public void setTimeStamp(final int timeStamp)
    {
        this.timeStamp = timeStamp;
    }

    public int getTimeUnit()
    {
        return timeUnit;
    }

    public void setTimeUnit(final int timeUnit)
    {
        this.timeUnit = timeUnit;
    }
}



