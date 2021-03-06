package com.codyrex.terravitae.server.block;

import com.codyrex.terravitae.Terravitae;
import com.codyrex.terravitae.server.api.BlockEntity;
import com.codyrex.terravitae.server.block.plant.DoublePlantBlock;
import com.codyrex.terravitae.server.block.plant.PlantBlock;
import com.codyrex.terravitae.server.plant.PlantSpawner;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class BlockHandler {
    public static final List<Block> BLOCKS = new ArrayList<>();

    public static final PlantBlock SAND_RYEGRASS = new PlantBlock("sand_ryegrass")
            .withSpawner(PlantSpawner.SINGLE_BEACH)
            .withBounds(PlantBlock.BUSH);
    public static final DoublePlantBlock GARDEN_ANGELICA = new DoublePlantBlock("garden_angelica")
            .withSpawner(PlantSpawner.DOUBLE_GROUND)
            .withBounds(PlantBlock.DOUBLE);
    public static final DoublePlantBlock UGANDAGRASS = new DoublePlantBlock("ugandagrass")
            .withSpawner(PlantSpawner.DOUBLE_GROUND)
            .withBounds(PlantBlock.DOUBLE);
    public static final DoublePlantBlock FLAMINGO_FLOWER = new DoublePlantBlock("flamingo_flower")
            .withSpawner(PlantSpawner.DOUBLE_GROUND)
            .withBounds(PlantBlock.DOUBLE);
    public static final PlantBlock MELOCACTUS_AZUREUS = new PlantBlock("melocactus_azureus")
            .withSpawner(PlantSpawner.SINGLE_BEACH)
            .withBounds(PlantBlock.BUSH);
    public static final DoublePlantBlock COMMON_BULRUSH = new DoublePlantBlock("common_bulrush")
            .withSpawner(PlantSpawner.DOUBLE_RIVER)
            .withBounds(PlantBlock.DOUBLE);
    public static final DoublePlantBlock GIGERIA_LUMINOSA = new DoublePlantBlock("gigeria_luminosa")
            .withSpawner(PlantSpawner.DOUBLE_END)
            .withBounds(PlantBlock.DOUBLE);
    public static final PlantBlock FIELD_FORGET_ME_NOT = new PlantBlock("field_forget_me_not")
            .withSpawner(PlantSpawner.SINGLE_GROUND);
    public static final PlantBlock GOLD_LACE_CACTUS = new PlantBlock("gold_lace_cactus")
            .withSpawner(PlantSpawner.SINGLE_BEACH)
            .withBounds(PlantBlock.BUSH);
    public static final PlantBlock ARIOCARPUS_KOTSCHOUBEYANUS = new PlantBlock("ariocarpus_kotschoubeyanus")
            .withSpawner(PlantSpawner.SINGLE_BEACH)
            .withBounds(PlantBlock.BUSH);
    public static final PlantBlock NERVE_PLANT = new PlantBlock("nerve_plant")
            .withSpawner(PlantSpawner.SINGLE_GROUND);
    public static final PlantBlock TEXAS_BLUEBONNET = new PlantBlock("texas_bluebonnet")
            .withSpawner(PlantSpawner.SINGLE_RIVER);
    public static final PlantBlock WESTERN_SWORD_FERN = new PlantBlock("western_sword-fern")
            .withSpawner(PlantSpawner.SINGLE_GROUND);


    public static void register() {
        try {
            for (Field field : BlockHandler.class.getDeclaredFields()) {
                Object obj = field.get(null);
                if (obj instanceof Block) {
                    BlockHandler.registerBlock((Block) obj);
                } else if (obj instanceof Block[]) {
                    for (Block block : (Block[]) obj) {
                        BlockHandler.registerBlock(block);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void registerBlock(Block block) {
        String name = block.getUnlocalizedName().substring("tile.".length());
        ResourceLocation identifier = new ResourceLocation(Terravitae.MODID, name);
        GameRegistry.register(block, identifier);
        GameRegistry.register(new ItemBlock(block), identifier);
        BLOCKS.add(block);
        if (block instanceof BlockEntity) {
            GameRegistry.registerTileEntity(((BlockEntity) block).getEntity(), Terravitae.MODID + "." + name);
        }
    }
}
