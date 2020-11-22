package tech.clayclaw.argoncore.javaadapter.util;

import dev.reactant.modulardata.DataModulesAccessor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataHolder;

public class ModularDataUtil {

    public static DataModulesAccessor getAccessorByItemStack(ItemStack itemStack) {
        ItemMeta itemMeta = getOrCreateItemMeta(itemStack);
        return new DataModulesAccessor(
                itemMeta::getPersistentDataContainer,
                () -> itemStack.setItemMeta(itemMeta)
        );
    }

    public static DataModulesAccessor getAccessor(PersistentDataHolder persistentDataHolder) {
        return new DataModulesAccessor(
                persistentDataHolder::getPersistentDataContainer,
                () -> null
        );
    }

    private static ItemMeta getOrCreateItemMeta(ItemStack itemStack) {
        if(!itemStack.hasItemMeta()) {
            if (itemStack.getType() == Material.AIR) throw new UnsupportedOperationException("Air do not have item meta");
            itemStack.setItemMeta(Bukkit.getItemFactory().getItemMeta(itemStack.getType()));
        }
        return itemStack.getItemMeta();
    }

}
