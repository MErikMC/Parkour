package com.merikmc.map.trophys;

import com.merikmc.map.highscores.Highscore;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.block.Skull;
import org.bukkit.inventory.ItemStack;

/**
 * Created by codermason on 12/24/14.
 */
public class TrophyBlock {

    private Location block, sign, head;

    public TrophyBlock(Location block, Location sign, Location head) {
        this.block = block;
        this.sign = sign;
        this.head = head;
    }

    public Location getHeadLocation() {
        return head;
    }

    public Location getSignLocation() {
        return sign;
    }

    public Location getBlockLocation() {
        return block;
    }

    public void update(Highscore highscore, int place) {
        if(highscore == null || (place < 1 || place > 5)) return;

        //update holding block
        if(block.getBlock().getType() != Material.OBSIDIAN) {
            block.getBlock().setType(Material.OBSIDIAN);
        }

        //update playerhead
        if(head.getBlock().getType() != Material.SKULL) {
            head.getBlock().setType(Material.SKULL);
        }

        Skull skull = (Skull) head.getBlock().getState();
        skull.setSkullType(SkullType.PLAYER);
        skull.setOwner(highscore.getName());
        skull.setRotation(BlockFace.NORTH);
        skull.setRotation(getDirection());
        head.getBlock().getState().update();

        //update sign
        if(sign.getBlock().getType() != Material.SIGN) {
            sign.getBlock().setType(Material.SIGN);
        }

        Sign signState = (Sign) sign.getBlock().getState();
        signState.setLine(1, ChatColor.GREEN + "#" + place);
        signState.setLine(2, ChatColor.BOLD + highscore.getName());
        signState.setLine(3, ChatColor.BOLD + highscore.getTime());
        signState.update();
    }

    private BlockFace getDirection() {
        Block signBlock = sign.getBlock(), blockBlock = block.getBlock(); //.... not proud
        if(signBlock.getRelative(BlockFace.NORTH) == blockBlock) {
            return BlockFace.SOUTH;
        } else if(signBlock.getRelative(BlockFace.SOUTH) == blockBlock) {
            return BlockFace.NORTH;
        } else if(signBlock.getRelative(BlockFace.EAST) == blockBlock) {
            return BlockFace.WEST;
        } else {
            return BlockFace.EAST;
        }
    }



}
