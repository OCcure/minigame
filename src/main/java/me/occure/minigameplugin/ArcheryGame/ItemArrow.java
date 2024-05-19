package me.occure.minigameplugin.ArcheryGame;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemArrow {

    public static ItemStack Type1Arrow(int a){
        ItemStack type1Arrow = new ItemStack(Material.ARROW,a);
        ItemMeta meta = type1Arrow.getItemMeta();
        meta.setDisplayName("일반 화살");
        List<String> lore = new ArrayList<>();
        lore.add("적중시 1칸의 블럭을 제거합니다");
        meta.setLore(lore);

        type1Arrow.setItemMeta(meta);

        return type1Arrow;
    }
    public static ItemStack Type2Arrow(int a){
        ItemStack type2Arrow = new ItemStack(Material.ARROW,a);
        ItemMeta meta = type2Arrow.getItemMeta();
        meta.setDisplayName("세로 화살");
        List<String> lore = new ArrayList<>();
        lore.add("적중시 적중한 과녁 블럭과 양 옆의 블럭을 제거합니다");
        meta.setLore(lore);

        type2Arrow.setItemMeta(meta);

        return type2Arrow;
    }
    public static ItemStack Type3Arrow(int a){
        ItemStack type3Arrow = new ItemStack(Material.ARROW,a);
        ItemMeta meta = type3Arrow.getItemMeta();
        meta.setDisplayName("가로 화살");
        List<String> lore = new ArrayList<>();
        lore.add("적중시 적중한 과녁블럭과 위아래 블록을 제거합니다");
        meta.setLore(lore);

        type3Arrow.setItemMeta(meta);

        return type3Arrow;
    }
}
