package me.elb1to.souppvp.controller;

import lombok.Getter;
import lombok.Setter;
import me.elb1to.souppvp.SoupPvP;
import me.elb1to.souppvp.utils.cuboid.Cuboid;
import me.elb1to.souppvp.utils.cuboid.CustomLocation;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Elb1to
 * Project: SoupPvP
 * Date: 7/18/2021 @ 12:06 AM
 */
@Getter @Setter
public class SpawnController {

    private final SoupPvP plugin = SoupPvP.getInstance();

    private final Map<UUID, BukkitTask> pendingTeleport = new HashMap<>();

    private CustomLocation spawnLocation;
    private CustomLocation safezoneMin;
    private CustomLocation safezoneMax;

    private Cuboid cuboid;

    public SpawnController() {
        this.loadConfig();
    }

    private void loadConfig() {
        final FileConfiguration config = this.plugin.getConfig();

        if (config.contains("SERVER.SPAWN.LOCATION")) {
            try {
                this.spawnLocation = CustomLocation.stringToLocation(config.getString("SERVER.SPAWN.LOCATION"));
                this.safezoneMin = CustomLocation.stringToLocation(config.getString("SERVER.SPAWN.SAFEZONE-MIN"));
                this.safezoneMax = CustomLocation.stringToLocation(config.getString("SERVER.SPAWN.SAFEZONE-MAX"));
                this.cuboid = new Cuboid(safezoneMin.toBukkitLocation(), safezoneMax.toBukkitLocation());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setupCuboid() {
        if (safezoneMin == null || safezoneMax == null) return; // we found skill issues here

        this.cuboid = new Cuboid(safezoneMin.toBukkitLocation(), safezoneMax.toBukkitLocation());
    }
}
