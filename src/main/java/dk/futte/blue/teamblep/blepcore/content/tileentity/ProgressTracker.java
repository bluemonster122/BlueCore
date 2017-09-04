package dk.futte.blue.teamblep.blepcore.content.tileentity;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kelan
 */
public class ProgressTracker
{
    private List<ProgressBar> progressBars = new ArrayList<>();

    private ProgressTracker()
    {
    }

    public static ProgressTracker create()
    {
        return new ProgressTracker();
    }

    public ProgressTracker addProgressBar(ProgressBar progressBar)
    {
        if (progressBar != null && !progressBars.contains(progressBar))
        {
            progressBars.add(progressBar);
        }

        return this;
    }

    public ProgressBar getProgressBar(String name)
    {
        for (ProgressBar progressBar : progressBars)
        {
            if (progressBar.getName().equals(name))
            {
                return progressBar;
            }
        }

        return null;
    }

    public List<ProgressBar> tick()
    {
        List<ProgressBar> ticked = new ArrayList<>();

        for (ProgressBar progressBar : progressBars)
        {
            if (progressBar.tick())
            {
                ticked.add(progressBar);
            }
        }

        return ticked;
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        for (ProgressBar progressBar : progressBars)
        {
            NBTTagCompound nbt = new NBTTagCompound();
            progressBar.writeToNBT(nbt);
            compound.setTag(progressBar.getName(), nbt);
        }

        return compound;
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        for (ProgressBar progressBar : progressBars)
        {
            if (compound.hasKey(progressBar.getName()))
            {
                NBTBase nbt = compound.getTag(progressBar.getName());

                if (nbt instanceof NBTTagCompound)
                {
                    progressBar.readFromNBT((NBTTagCompound) nbt);
                }
            }
        }
    }
}