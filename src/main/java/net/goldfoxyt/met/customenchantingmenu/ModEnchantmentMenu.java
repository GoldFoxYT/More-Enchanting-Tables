package net.goldfoxyt.met.customenchantingmenu;

import com.mojang.datafixers.util.Pair;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import net.goldfoxyt.met.block.ColoredEnchantingTable;
import net.minecraft.Util;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.IdMap;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.block.EnchantingTableBlock;
import net.minecraftforge.common.Tags.Items;
import net.minecraftforge.event.ForgeEventFactory;

public class ModEnchantmentMenu extends AbstractContainerMenu {
    static final ResourceLocation EMPTY_SLOT_LAPIS_LAZULI = ResourceLocation.withDefaultNamespace("item/empty_slot_lapis_lazuli");
    private final Container enchantSlots;
    private final ContainerLevelAccess access;
    private final RandomSource random;
    private final DataSlot enchantmentSeed;
    public final int[] costs;
    public final int[] enchantClue;
    public final int[] levelClue;
    private Player player;

    public ModEnchantmentMenu(int pContainerId, Inventory pPlayerInventory) {
        this(pContainerId, pPlayerInventory, ContainerLevelAccess.NULL);
    }

    public ModEnchantmentMenu(int pContainerId, Inventory pPlayerInventory, ContainerLevelAccess pAccess) {
        super(MenuType.ENCHANTMENT, pContainerId);
        this.enchantSlots = new SimpleContainer(2) {
            public void setChanged() {
                super.setChanged();
                ModEnchantmentMenu.this.slotsChanged(this);
            }
        };
        this.random = RandomSource.create();
        this.enchantmentSeed = DataSlot.standalone();
        this.costs = new int[3];
        this.enchantClue = new int[]{-1, -1, -1};
        this.levelClue = new int[]{-1, -1, -1};
        this.access = pAccess;
        this.addSlot(new Slot(this.enchantSlots, 0, 15, 47) {
            public int getMaxStackSize() {
                return 1;
            }
        });
        this.addSlot(new Slot(this.enchantSlots, 1, 35, 47) {
            public boolean mayPlace(ItemStack pStack) {
                return pStack.is(Items.ENCHANTING_FUELS);
            }

            public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                return Pair.of(InventoryMenu.BLOCK_ATLAS, ModEnchantmentMenu.EMPTY_SLOT_LAPIS_LAZULI);
            }
        });

        int k;
        for(k = 0; k < 3; ++k) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(pPlayerInventory, j + k * 9 + 9, 8 + j * 18, 84 + k * 18));
            }
        }

        for(k = 0; k < 9; ++k) {
            this.addSlot(new Slot(pPlayerInventory, k, 8 + k * 18, 142));
        }

        this.addDataSlot(DataSlot.shared(this.costs, 0));
        this.addDataSlot(DataSlot.shared(this.costs, 1));
        this.addDataSlot(DataSlot.shared(this.costs, 2));
        this.addDataSlot(this.enchantmentSeed).set(pPlayerInventory.player.getEnchantmentSeed());
        this.addDataSlot(DataSlot.shared(this.enchantClue, 0));
        this.addDataSlot(DataSlot.shared(this.enchantClue, 1));
        this.addDataSlot(DataSlot.shared(this.enchantClue, 2));
        this.addDataSlot(DataSlot.shared(this.levelClue, 0));
        this.addDataSlot(DataSlot.shared(this.levelClue, 1));
        this.addDataSlot(DataSlot.shared(this.levelClue, 2));
    }

    public void slotsChanged(Container pInventory) {
        if (pInventory == this.enchantSlots) {
            ItemStack itemstack = pInventory.getItem(0);
            if (!itemstack.isEmpty() && itemstack.isEnchantable()) {
                this.access.execute((p_341515_, p_341516_) -> {
                    IdMap<Holder<Enchantment>> idmap = p_341515_.registryAccess().registryOrThrow(Registries.ENCHANTMENT).asHolderIdMap();
                    float j = 0.0F;
                    Iterator var6 = EnchantingTableBlock.BOOKSHELF_OFFSETS.iterator();

                    while(var6.hasNext()) {
                        BlockPos blockpos = (BlockPos)var6.next();
                        if (EnchantingTableBlock.isValidBookShelf(p_341515_, p_341516_, blockpos)) {
                            ++j;
                            j += p_341515_.getBlockState(p_341516_.offset(blockpos)).getEnchantPowerBonus(p_341515_, p_341516_.offset(blockpos));
                        }
                    }

                    this.random.setSeed((long)this.enchantmentSeed.get());

                    int l;
                    for(l = 0; l < 3; ++l) {
                        this.costs[l] = EnchantmentHelper.getEnchantmentCost(this.random, l, (int)j, itemstack);
                        this.enchantClue[l] = -1;
                        this.levelClue[l] = -1;
                        if (this.costs[l] < l + 1) {
                            this.costs[l] = 0;
                        }

                        this.costs[l] = ForgeEventFactory.onEnchantmentLevelSet(p_341515_, p_341516_, l, (int)j, itemstack, this.costs[l]);
                    }

                    for(l = 0; l < 3; ++l) {
                        if (this.costs[l] > 0) {
                            List<EnchantmentInstance> list = this.getEnchantmentList(p_341515_.registryAccess(), itemstack, l, this.costs[l]);
                            if (list != null && !list.isEmpty()) {
                                EnchantmentInstance enchantmentinstance = (EnchantmentInstance)list.get(this.random.nextInt(list.size()));
                                this.enchantClue[l] = idmap.getId(enchantmentinstance.enchantment);
                                this.levelClue[l] = enchantmentinstance.level;
                            }
                        }
                    }

                    this.broadcastChanges();
                });
            } else {
                for(int i = 0; i < 3; ++i) {
                    this.costs[i] = 0;
                    this.enchantClue[i] = -1;
                    this.levelClue[i] = -1;
                }
            }
        }

    }

    public boolean clickMenuButton(Player pPlayer, int pId) {
        if (pId >= 0 && pId < this.costs.length) {
            ItemStack itemstack = this.enchantSlots.getItem(0);
            ItemStack itemstack1 = this.enchantSlots.getItem(1);
            int i = pId + 1;
            if ((itemstack1.isEmpty() || itemstack1.getCount() < i) && !pPlayer.hasInfiniteMaterials()) {
                return false;
            } else if (this.costs[pId] > 0 && !itemstack.isEmpty() && (pPlayer.experienceLevel >= i && pPlayer.experienceLevel >= this.costs[pId] || pPlayer.getAbilities().instabuild)) {
                this.access.execute((p_341512_, p_341513_) -> {
                    ItemStack itemstack2 = itemstack;
                    List<EnchantmentInstance> list = this.getEnchantmentList(p_341512_.registryAccess(), itemstack, pId, this.costs[pId]);
                    if (!list.isEmpty()) {
                        pPlayer.onEnchantmentPerformed(itemstack, i);
                        if (itemstack.is(net.minecraft.world.item.Items.BOOK)) {
                            itemstack2 = itemstack.transmuteCopy(net.minecraft.world.item.Items.ENCHANTED_BOOK);
                            this.enchantSlots.setItem(0, itemstack2);
                        }

                        Iterator var10 = list.iterator();

                        while(var10.hasNext()) {
                            EnchantmentInstance enchantmentinstance = (EnchantmentInstance)var10.next();
                            itemstack2.enchant(enchantmentinstance.enchantment, enchantmentinstance.level);
                        }

                        itemstack1.consume(i, pPlayer);
                        if (itemstack1.isEmpty()) {
                            this.enchantSlots.setItem(1, ItemStack.EMPTY);
                        }

                        pPlayer.awardStat(Stats.ENCHANT_ITEM);
                        if (pPlayer instanceof ServerPlayer) {
                            CriteriaTriggers.ENCHANTED_ITEM.trigger((ServerPlayer)pPlayer, itemstack2, i);
                        }

                        this.enchantSlots.setChanged();
                        this.enchantmentSeed.set(pPlayer.getEnchantmentSeed());
                        this.slotsChanged(this.enchantSlots);
                        p_341512_.playSound((Player)null, p_341513_, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS, 1.0F, p_341512_.random.nextFloat() * 0.1F + 0.9F);
                    }

                });
                return true;
            } else {
                return false;
            }
        } else {
            String var10000 = String.valueOf(pPlayer.getName());
            Util.logAndPauseIfInIde(var10000 + " pressed invalid button id: " + pId);
            return false;
        }
    }

    private List<EnchantmentInstance> getEnchantmentList(RegistryAccess pRegistryAccess, ItemStack pStack, int pSlot, int pCost) {
        this.random.setSeed((long)(this.enchantmentSeed.get() + pSlot));
        Optional<HolderSet.Named<Enchantment>> optional = pRegistryAccess.registryOrThrow(Registries.ENCHANTMENT).getTag(EnchantmentTags.IN_ENCHANTING_TABLE);
        if (optional.isEmpty()) {
            return List.of();
        } else {
            List<EnchantmentInstance> list = EnchantmentHelper.selectEnchantment(this.random, pStack, pCost, ((HolderSet.Named)optional.get()).stream());
            if (pStack.is(net.minecraft.world.item.Items.BOOK) && list.size() > 1) {
                list.remove(this.random.nextInt(list.size()));
            }

            return list;
        }
    }

    public int getGoldCount() {
        ItemStack itemstack = this.enchantSlots.getItem(1);
        return itemstack.isEmpty() ? 0 : itemstack.getCount();
    }

    public int getEnchantmentSeed() {
        return this.enchantmentSeed.get();
    }

    public void removed(Player pPlayer) {
        super.removed(pPlayer);
        this.access.execute((p_39469_, p_39470_) -> {
            this.clearContainer(pPlayer, this.enchantSlots);
        });
    }
    @Override
    public boolean stillValid(Player player) {
        return this.enchantSlots.stillValid(player);
    }

    public boolean stillValid(Player pPlayer, List<ColoredEnchantingTable> blocks) {
        for (ColoredEnchantingTable block : blocks) {
            if (stillValid(this.access, pPlayer, block)) {
                return true;
            }
        }
        return false;
    }

    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = (Slot)this.slots.get(pIndex);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (pIndex == 0) {
                if (!this.moveItemStackTo(itemstack1, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (pIndex == 1) {
                if (!this.moveItemStackTo(itemstack1, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (itemstack1.is(Items.ENCHANTING_FUELS)) {
                if (!this.moveItemStackTo(itemstack1, 1, 2, true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (((Slot)this.slots.get(0)).hasItem() || !((Slot)this.slots.get(0)).mayPlace(itemstack1)) {
                    return ItemStack.EMPTY;
                }

                ItemStack itemstack2 = itemstack1.copyWithCount(1);
                itemstack1.shrink(1);
                ((Slot)this.slots.get(0)).setByPlayer(itemstack2);
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(pPlayer, itemstack1);
        }

        return itemstack;
    }
}
