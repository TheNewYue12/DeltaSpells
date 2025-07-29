package com.thenewyue12.deltaspells;

import com.thenewyue12.deltaspells.entities.renderers.RendererHelpers.SpellRendererHelper;
import com.thenewyue12.deltaspells.entities.renderers.rudebuster.RudeBusterRenderer;
import com.thenewyue12.deltaspells.particles.RudeBusterParticles;
import com.thenewyue12.deltaspells.registries.*;
import com.thenewyue12.deltaspells.setup.ClientSetup;
import io.redspace.ironsspellbooks.registries.ParticleRegistry;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.BuiltInPackSource;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.fml.common.EventBusSubscriber;

import java.io.IOException;
import java.util.Optional;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(DeltaSpells.MODID)
public class DeltaSpells {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "deltaspells";
    // Directly reference a slf4j logger

    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "deltaspells" namespace
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "deltaspells" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "deltaspells" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // Creates a new food item with the id "deltaspells:example_id", nutrition 1 and saturation 2

    // Creates a creative tab with the id "deltaspells:example_tab" for the example ite
;

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.

    public DeltaSpells(IEventBus modEventBus, ModContainer modContainer) {

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.register(this);
        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        DSSpellRegistries.register(modEventBus);

        DSSchoolRegistries.register(modEventBus);

        DSEntityRegistry.register(modEventBus);

        DSAttributeRegistry.register(modEventBus);

        DSSoundRegistry.register(modEventBus);

        DSParticlesRegistry.register(modEventBus);
        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (DeltaSpells) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.LOG_DIRT_BLOCK.getAsBoolean()) {
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
        }

        LOGGER.info("{}{}", Config.MAGIC_NUMBER_INTRODUCTION.get(), Config.MAGIC_NUMBER.getAsInt());

        Config.ITEM_STRINGS.get().forEach((item) -> LOGGER.info("ITEM >> {}", item));
    }
    public void addPackFinders(AddPackFindersEvent event) {
        DeltaSpells.LOGGER.debug("addPackFinders");
        try {
            if (event.getPackType() == PackType.CLIENT_RESOURCES) {
                addBuiltinPack(event, "deltaspells_misc_music", Component.literal("Legacy Dead King"));
            }
        } catch (IOException ex) {
            DeltaSpells.LOGGER.error("Failed to load a builtin resource pack! If you are seeing this message, please report an issue to https://github.com/iron431/Irons-Spells-n-Spellbooks/issues");
            // throw new RuntimeException(ex);
        }
    }
    private static void addBuiltinPack(AddPackFindersEvent event, String filename, Component displayName) throws IOException {
        filename = "builtin_resource_packs/" + filename;
        String id = "builtin/" + filename;
        var resourcePath = ModList.get().getModFileById(MODID).getFile().findResource(filename);
        var pack = Pack.readMetaAndCreate(
                new PackLocationInfo(id, displayName, PackSource.BUILT_IN, Optional.empty()),
                BuiltInPackSource.fromName((path) -> new PathPackResources(path, resourcePath)),
                PackType.CLIENT_RESOURCES,
                new PackSelectionConfig(false, Pack.Position.TOP, false)
        );
        event.addRepositorySource((packConsumer) -> packConsumer.accept(pack));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent

    @EventBusSubscriber(modid = DeltaSpells.MODID, bus = EventBusSubscriber.Bus.MOD)

    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {


        }

    }

    public static ResourceLocation id(@NotNull String path) {
        return new ResourceLocation(DeltaSpells.MODID, path);
    }
}
