package net.gamerk_2.tetra_re_enlarged.util;

import net.gamerk_2.tetra_re_enlarged.TetraReEnlarged;
import net.gamerk_2.tetra_re_enlarged.item.ModularLargeBladedItem;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = TetraReEnlarged.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class HeadDropHandler {
    private static final float BASE_HEAD_DROP_CHANCE = 0.25f;

    @SubscribeEvent
    public static void onLivingDrops(LivingDropsEvent event) {
        Entity direct = event.getSource().getEntity();
        if (direct != null) {
            if (!direct.level().isClientSide()){
                if (!(direct instanceof Player player)) return;

                ItemStack heldItem = player.getMainHandItem();
                if (!(heldItem.getItem() instanceof ModularLargeBladedItem)) return;
                String bladeModule = Objects.requireNonNull(heldItem.getTag()).getString("greatsword/blade");

                if (!"greatsword/heavy_blade".equals(bladeModule)) return;

                LivingEntity entity = event.getEntity();
                Level level = entity.level();

                ItemStack headStack = null;
                if (entity instanceof WitherSkeleton) headStack = new ItemStack(Items.WITHER_SKELETON_SKULL);
                else if (entity instanceof Skeleton) headStack = new ItemStack(Items.SKELETON_SKULL);
                else if (entity instanceof Zombie) headStack = new ItemStack(Items.ZOMBIE_HEAD);
                else if (entity instanceof Creeper) headStack = new ItemStack(Items.CREEPER_HEAD);

                if (headStack == null) return;

                RandomSource rand = entity.getRandom();
                if (rand.nextFloat() < BASE_HEAD_DROP_CHANCE) {
                    ItemEntity drop = new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), headStack);
                    drop.setPickUpDelay(10);
                    event.getDrops().add(drop);
                }
            }
        }
    }
}
