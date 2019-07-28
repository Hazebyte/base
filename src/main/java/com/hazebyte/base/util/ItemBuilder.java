package com.hazebyte.base.util;


import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.Wool;

import java.util.*;

public class ItemBuilder {
    private ItemStack itemStack;

    public ItemBuilder(Material type) {
        this.itemStack = new ItemStack(type);
    }

    public ItemBuilder(Material type, int amount) {
        this.itemStack = new ItemStack(type, amount);
    }

    public ItemBuilder(Material type, int amount, short durability) {
        this.itemStack = new ItemStack(type, amount, durability);
    }

    public ItemBuilder(Material type, int amount, short durability, String displayName) {
        this.itemStack = new ItemStack(type, amount, durability);
        this.displayName(displayName);
    }

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public static ItemBuilder clone(ItemStack itemStack) {
        return of(itemStack.clone());
    }

    public static ItemBuilder of(ItemStack itemStack) {
        Optional<ItemStack> optional = Optional.ofNullable(itemStack);
        return optional.isPresent() ? new ItemBuilder((ItemStack) optional.get()) : null;
    }

    public ItemBuilder type(Material type) {
        this.itemStack.setType(type);
        return this;
    }

    public ItemBuilder amount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder durability(short durability) {
        this.itemStack.setDurability(durability);
        return this;
    }

    public ItemBuilder displayName(String displayName) {
        ItemMeta meta = this.itemStack.getItemMeta();
        meta.setDisplayName(Replacer.replace(displayName));
        this.itemStack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder lore(String... lines) {
        this.lore(Arrays.asList(lines));
        return this;
    }

    public ItemBuilder lore(List<String> lore) {
        ItemMeta meta = this.itemStack.getItemMeta();
        meta.setLore(Replacer.replace(lore));
        this.itemStack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder append(String... lines) {
        return this.append(Arrays.asList(lines));
    }

    public ItemBuilder append(List<String> lines) {
        ItemMeta meta = this.itemStack.getItemMeta();
        Optional<List<String>> optional = Optional.ofNullable(meta.getLore());
        List<String> lore = (List) optional.orElse(new ArrayList());
        lore.addAll(lines);
        this.lore(lore);
        return this;
    }

    public ItemBuilder enchants(Map<Enchantment, Integer> enchants) {
        ItemMeta meta = this.itemStack.getItemMeta();
        Iterator var3 = enchants.entrySet().iterator();

        while (var3.hasNext()) {
            Map.Entry<Enchantment, Integer> entry = (Map.Entry) var3.next();
            meta.addEnchant((Enchantment) entry.getKey(), (Integer) entry.getValue(), true);
        }

        this.itemStack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder unsafeEnchants(Map<Enchantment, Integer> enchants) {
        Iterator var2 = enchants.entrySet().iterator();

        while (var2.hasNext()) {
            Map.Entry<Enchantment, Integer> entry = (Map.Entry) var2.next();
            this.itemStack.addUnsafeEnchantment((Enchantment) entry.getKey(), (Integer) entry.getValue());
        }

        return this;
    }

    public ItemBuilder enchant(Enchantment enchantment, int level) {
        ItemMeta meta = this.itemStack.getItemMeta();
        meta.addEnchant(enchantment, level, false);
        this.itemStack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder unsafeEnchant(Enchantment enchantment, int level) {
        this.itemStack.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public ItemBuilder removeEnchants() {
        ItemMeta meta = this.itemStack.getItemMeta();
        Map<Enchantment, Integer> enchantments = meta.getEnchants();
        enchantments.keySet().forEach((enchantment) -> {
            Integer var10000 = (Integer) enchantments.remove(enchantment);
        });
        this.itemStack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder flags(ItemFlag... flags) {
        ItemMeta meta = this.itemStack.getItemMeta();
        meta.addItemFlags(flags);
        this.itemStack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder flag(ItemFlag flag) {
        this.flags(flag);
        return this;
    }

    public ItemBuilder unbreakable(boolean unbreakable) {
        ItemMeta meta = this.itemStack.getItemMeta();
        meta.spigot().setUnbreakable(unbreakable);
        this.itemStack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder hideAll(boolean hide) {
        return this.flag(ItemFlag.HIDE_ENCHANTS).flag(ItemFlag.HIDE_ATTRIBUTES).flag(ItemFlag.HIDE_UNBREAKABLE).flag(ItemFlag.HIDE_POTION_EFFECTS).flag(ItemFlag.HIDE_DESTROYS);
    }

    public ItemBuilder woolColor(DyeColor color) {
        if (this.itemStack != null && this.itemStack.getType() == Material.matchMaterial("WOOL")) {
            Wool wool = new Wool(color);
            this.itemStack.setDurability(wool.toItemStack().getDurability());
        }

        return this;
    }

    public ItemBuilder skull(SkullMeta meta) {
        if (this.itemStack != null && this.itemStack.getType() == Material.matchMaterial("SKULL")) {
            this.itemStack.setItemMeta(meta);
        }

        return this;
    }

    public ItemBuilder setGlowing(boolean glowing) {
        if (glowing) {
            this.unsafeEnchant(this.itemStack.getType() != Material.BOW ? Enchantment.ARROW_INFINITE : Enchantment.LUCK, 10);
            this.flag(ItemFlag.HIDE_ENCHANTS);
        }
        return this;
    }

    public ItemStack asItemStack() {
        return this.itemStack;
    }

    public static enum DataType {
        FLOAT,
        DOUBLE,
        STRING,
        BYTEARRAY,
        INTARRAY,
        BOOLEAN;

        private DataType() {
        }
    }
}