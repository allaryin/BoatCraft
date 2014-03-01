package k2b6s9j.boatcraft.core

import cpw.mods.fml.client.registry.RenderingRegistry
import cpw.mods.fml.common.registry.EntityRegistry
import net.minecraftforge.client.MinecraftForgeClient
import k2b6s9j.boatcraft.api.Boat

/** Contains the Proxy classes needed for the Minecraft Client and Server to function properly. */
object Proxy
{
  /**
    * The base proxy class.
    * Used for the Minecraft Server.
    */
  class CommonProxy
	{
    /**
      * The register renderers method.
      * Not used client-side, but it needs to be here so that the client functions correctly.
      */
    def registerRenderers
		{}

    /**
      * The register entities method.
      * Used to register the entities created by the mod with Minecraft Forge, which configures the entity for both server and client.
      */
    def registerEntities
		{
			EntityRegistry.registerModEntity(classOf[Boat.EntityCustomBoat],
					"customBoat", 0, BoatCraft, 66, 10, true)
			EntityRegistry.registerModEntity(classOf[Boat.EntityBoatContainer],
					"containerBoat", 1, BoatCraft, 66, 10, true)
		}
	}

  /** The client proxy class, extending the base proxy. */
  class ClientProxy extends CommonProxy
	{
    /**
     * The overridden register renderers method, used to register special rendering done by the mod with the client.
     */
    override def registerRenderers
		{
			BoatCraft.log info "Registering Renderes"
			RenderingRegistry registerEntityRenderingHandler(classOf[Boat.EntityCustomBoat],
					new Boat.RenderCustomBoat)
			MinecraftForgeClient registerItemRenderer(BoatCraft.itemBoat, new Boat.RenderCustomBoat)
			RenderingRegistry registerEntityRenderingHandler(classOf[Boat.EntityBoatContainer],
					new Boat.RenderCustomBoat)
		}
	}
}