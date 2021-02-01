package Moonworks.cards;

import Moonworks.OrangeJuiceMod;
import Moonworks.cards.abstractCards.AbstractNormaAttentiveCard;
import Moonworks.characters.TheStarBreaker;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Moonworks.OrangeJuiceMod.makeCardPath;

public class BlueCrowTheSecond extends AbstractNormaAttentiveCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Defend Gain 5 (8) block.
     */


    // TEXT DECLARATION

    public static final String ID = OrangeJuiceMod.makeID(BlueCrowTheSecond.class.getSimpleName());
    public static final String IMG = makeCardPath("BlueCrowTheSecond.png");

    //private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    //public static final String SPENT_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheStarBreaker.Enums.COLOR_WHITE_ICE;

    private static final int COST = 1;

    private static final int INITIAL_VALS = 1;

    private boolean preview = false;

    // /STAT DECLARATION/


    public BlueCrowTheSecond() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.block = this.baseBlock = INITIAL_VALS;
        this.damage = this.baseDamage = INITIAL_VALS;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        //Deal damage
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));

        //Gain Block
        this.addToBot(new GainBlockAction(p, p, block));
    }

    @Override
    public void applyPowers() {
        baseDamage = baseBlock = AbstractDungeon.player.hand.size();
        super.applyPowers();
        baseDamage = baseBlock = 1;
        isBlockModified = block != baseBlock;
        isDamageModified = damage != baseDamage;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        baseDamage = baseBlock = AbstractDungeon.player.hand.size();
        super.calculateCardDamage(mo);
        baseDamage = baseBlock = 1;
        isBlockModified = block != baseBlock;
        isDamageModified = damage != baseDamage;
    }

    @Override
    public float getTitleFontSize() {
        return (upgraded || preview) ? 18F : super.getTitleFontSize();
    }

    @Override
    protected void upgradeName() {
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = EXTENDED_DESCRIPTION[0];
        this.initializeTitle();
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.selfRetain = true;
            preview = true;
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
