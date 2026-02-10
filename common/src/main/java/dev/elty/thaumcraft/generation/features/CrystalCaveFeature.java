package dev.elty.thaumcraft.generation.features;

import dev.elty.thaumcraft.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class CrystalCaveFeature extends Feature<NoneFeatureConfiguration> {

    public CrystalCaveFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel world = context.level();
        BlockPos pos = context.origin();
        for (Direction direction : Direction.values()) {
            BlockState state = world.getBlockState(pos.relative(direction));
            if (direction == Direction.DOWN) {
                if (state.isAir() || !state.is(BlockTags.BASE_STONE_OVERWORLD))
                    return false;
            }  else if (!state.isAir())
                return false;
        }
        world.setBlock(pos, ModBlocks.CRYSTAL.get().defaultBlockState(), 2);
        return true;
    }
}
