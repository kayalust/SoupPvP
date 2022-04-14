package me.elb1to.souppvp.task;

import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author kayalust
 * @since 13/4/2022
 */

@RequiredArgsConstructor
public class SpawnCountdownTask extends BukkitRunnable {

    private final Player player;
    private int time;

    @Override
    public void run() {

        time--;
    }
}
