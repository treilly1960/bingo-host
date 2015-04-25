package bingo;

import java.util.BitSet;
import java.lang.String;

//! A winning card class.
/*!
  A winning card class which provides winning bingo patterns as sets of
  32-bit BitSet's.  The most significant 7-bits are unused and are set
  to zero.
*/
public class WinningCard
{
    // Row.
    private final BitSet row0;
    private final BitSet row1;
    private final BitSet row2;
    private final BitSet row3;
    private final BitSet row4;

    // Column.
    private final BitSet col0;
    private final BitSet col1;
    private final BitSet col2;
    private final BitSet col3;
    private final BitSet col4;

    // Airplane
    private final BitSet airplane0;
    private final BitSet airplane1;
    private final BitSet airplane2;
    private final BitSet airplane3;

    // Arrowhead.
    private final BitSet arrowhead0;
    private final BitSet arrowhead1;
    private final BitSet arrowhead2;
    private final BitSet arrowhead3;

    // Barbell.
    private final BitSet barbell0;
    private final BitSet barbell1;
    private final BitSet barbell2;

    // BlockOf6.
    private final BitSet blockofsix0;
    private final BitSet blockofsix1;
    private final BitSet blockofsix2;
    private final BitSet blockofsix3;
    private final BitSet blockofsix4;
    private final BitSet blockofsix5;
    private final BitSet blockofsix6;
    private final BitSet blockofsix7;
    private final BitSet blockofsix8;
    private final BitSet blockofsix9;
    private final BitSet blockofsix10;
    private final BitSet blockofsix11;
    private final BitSet blockofsix12;
    private final BitSet blockofsix13;
    private final BitSet blockofsix14;
    private final BitSet blockofsix15;
    private final BitSet blockofsix16;
    private final BitSet blockofsix17;
    private final BitSet blockofsix18;
    private final BitSet blockofsix19;
    private final BitSet blockofsix20;
    private final BitSet blockofsix21;
    private final BitSet blockofsix22;
    private final BitSet blockofsix23;

    // BlockOf8.
    private final BitSet blockofeight0;
    private final BitSet blockofeight1;
    private final BitSet blockofeight2;
    private final BitSet blockofeight3;
    private final BitSet blockofeight4;
    private final BitSet blockofeight5;
    private final BitSet blockofeight6;
    private final BitSet blockofeight7;
    private final BitSet blockofeight8;
    private final BitSet blockofeight9;
    private final BitSet blockofeight10;
    private final BitSet blockofeight11;
    private final BitSet blockofeight12;
    private final BitSet blockofeight13;
    private final BitSet blockofeight14;
    private final BitSet blockofeight15;

    // BlockOf9.
    private final BitSet blockofnine0;
    private final BitSet blockofnine1;
    private final BitSet blockofnine2;
    private final BitSet blockofnine3;
    private final BitSet blockofnine4;
    private final BitSet blockofnine5;
    private final BitSet blockofnine6;
    private final BitSet blockofnine7;
    private final BitSet blockofnine8;

    // BowTie1.
    private final BitSet bowtieone0;

    // BowTie2.
    private final BitSet bowtietwo0;
    private final BitSet bowtietwo1;

    // Cents.
    private final BitSet cents0;
    private final BitSet cents1;
    private final BitSet cents2;

    // Champagne Glass Empty.
    private final BitSet champagneglassempty0;

    // Champagne Glass Full.
    private final BitSet champagneglassfull0;

    // Checkmark.
    private final BitSet checkmark0;
    private final BitSet checkmark1;

    // Diamond.
    private final BitSet diamond0;

    // Diamond Inside.
    private final BitSet diamondinside0;

    // Diamond Outline.
    private final BitSet diamondoutline0;

    // Dollar Sign.
    private final BitSet dollarsign0;

    // Down Diagonal.
    private final BitSet downdiagonal0;

    // Field Goal.
    private final BitSet fieldgoal0;

    // Four Corners Big.
    private final BitSet fourcornersbig0;

    // Four Corners Small.
    private final BitSet fourcornerssmall0;

    // Frame Inside.
    private final BitSet frameinside0;

    // Kite.
    private final BitSet kite0;
    private final BitSet kite1;

    // Ladder.
    private final BitSet ladder0;
    private final BitSet ladder1;
    private final BitSet ladder2;

    // Letter M.
    private final BitSet letterm0;

    // Letter P.
    private final BitSet letterp0;

    // Letter S.
    private final BitSet letters0;

    // Letter W.
    private final BitSet letterw0;

    // Letter Y.
    private final BitSet lettery0;

    // Love Letter.
    private final BitSet loveletter0;

    // Percent Sign.
    private final BitSet percentsign0;

    // Pyramid.
    private final BitSet pyramid0;

    // Sputnik.
    private final BitSet sputnik0;

    // Stamp1.
    private final BitSet stampone0;
    private final BitSet stampone1;
    private final BitSet stampone2;
    private final BitSet stampone3;

    // Stamp2.
    private final BitSet stamptwo0;
    private final BitSet stamptwo1;
    private final BitSet stamptwo2;
    private final BitSet stamptwo3;
    private final BitSet stamptwo4;
    private final BitSet stamptwo5;

    // Stamp4.
    private final BitSet stampfour0;

    // Stamp and 4 Corners.
    private final BitSet stampand3corners0;
    private final BitSet stampand3corners1;
    private final BitSet stampand3corners2;
    private final BitSet stampand3corners3;

    // Stamp and Line.
    private final BitSet stampandline0;
    private final BitSet stampandline1;
    private final BitSet stampandline2;
    private final BitSet stampandline3;

    // Straight and Corners.
    private final BitSet straightandcorners0;
    private final BitSet straightandcorners1;

    // TopHat.
    private final BitSet tophat0;

    // Tree.
    private final BitSet tree0;

    // Turtle.
    private final BitSet turtle0;
    private final BitSet turtle1;
    private final BitSet turtle2;
    private final BitSet turtle3;

    // Up Diagonal.
    private final BitSet updiagonal0;
    
    //! A default constructor.
    /*!
      A default constructor which initializes the winning bingo patterns.
    */
    public WinningCard() {
        row0 = new BitSet(25);
        row0.set(0);
        row0.set(5);
        row0.set(10);
        row0.set(15);
        row0.set(20);

        row1 = new BitSet(25);
        row1.set(1);
        row1.set(6);
        row1.set(11);
        row1.set(16);
        row1.set(21);

        row2 = new BitSet(25);
        row2.set(2);
        row2.set(7);
        row2.set(12);
        row2.set(17);
        row2.set(22);

        row3 = new BitSet(25);
        row3.set(3);
        row3.set(8);
        row3.set(13);
        row3.set(18);
        row3.set(23);

        row4 = new BitSet(25);
        row4.set(4);
        row4.set(9);
        row4.set(14);
        row4.set(19);
        row4.set(24);

        col0 = new BitSet(25);
        col0.set(0);
        col0.set(1);
        col0.set(2);
        col0.set(3);
        col0.set(4);

        col1 = new BitSet(25);
        col1.set(5);
        col1.set(6);
        col1.set(7);
        col1.set(8);
        col1.set(9);

        col2 = new BitSet(25);
        col2.set(10);
        col2.set(11);
        col2.set(12);
        col2.set(13);
        col2.set(14);

        col3 = new BitSet(25);
        col3.set(15);
        col3.set(16);
        col3.set(17);
        col3.set(18);
        col3.set(19);

        col4 = new BitSet(25);
        col4.set(20);
        col4.set(21);
        col4.set(22);
        col4.set(23);
        col4.set(24);

        downdiagonal0 = new BitSet(25);
        downdiagonal0.set(0);
        downdiagonal0.set(6);
        downdiagonal0.set(12);
        downdiagonal0.set(18);
        downdiagonal0.set(24);

        updiagonal0 = new BitSet(25);
        updiagonal0.set(4);
        updiagonal0.set(8);
        updiagonal0.set(12);
        updiagonal0.set(16);
        updiagonal0.set(20);

        airplane0 = new BitSet(25);
        airplane0.set(1);
        airplane0.set(2);
        airplane0.set(3);
        airplane0.set(7);
        airplane0.set(12);
        airplane0.set(15);
        airplane0.set(16);
        airplane0.set(17);
        airplane0.set(18);
        airplane0.set(19);
        airplane0.set(22);

        airplane1 = new BitSet(25);
        airplane1.set(1);
        airplane1.set(6);
        airplane1.set(9);
        airplane1.set(10);
        airplane1.set(11);
        airplane1.set(12);
        airplane1.set(13);
        airplane1.set(14);
        airplane1.set(16);
        airplane1.set(19);
        airplane1.set(21);

        airplane2 = new BitSet(25);
        airplane2.set(2);
        airplane2.set(5);
        airplane2.set(6);
        airplane2.set(7);
        airplane2.set(8);
        airplane2.set(9);
        airplane2.set(12);
        airplane2.set(17);
        airplane2.set(21);
        airplane2.set(22);
        airplane2.set(23);

        airplane3 = new BitSet(25);
        airplane3.set(3);
        airplane3.set(5);
        airplane3.set(8);
        airplane3.set(10);
        airplane3.set(11);
        airplane3.set(12);
        airplane3.set(13);
        airplane3.set(14);
        airplane3.set(15);
        airplane3.set(18);
        airplane3.set(23);

        arrowhead0 = new BitSet(25);
        arrowhead0.set(0);
        arrowhead0.set(1);
        arrowhead0.set(2);
        arrowhead0.set(5);
        arrowhead0.set(6);
        arrowhead0.set(10);

        arrowhead1 = new BitSet(25);
        arrowhead1.set(10);
        arrowhead1.set(15);
        arrowhead1.set(16);
        arrowhead1.set(20);
        arrowhead1.set(21);
        arrowhead1.set(22);

        arrowhead2 = new BitSet(25);
        arrowhead2.set(14);
        arrowhead2.set(18);
        arrowhead2.set(19);
        arrowhead2.set(22);
        arrowhead2.set(23);
        arrowhead2.set(24);

        arrowhead3 = new BitSet(25);
        arrowhead3.set(2);
        arrowhead3.set(3);
        arrowhead3.set(4);
        arrowhead3.set(8);
        arrowhead3.set(9);
        arrowhead3.set(14);

        barbell0 = new BitSet(25);
        barbell0.set(0);
        barbell0.set(1);
        barbell0.set(2);
        barbell0.set(6);
        barbell0.set(11);
        barbell0.set(16);
        barbell0.set(20);
        barbell0.set(21);
        barbell0.set(22);

        barbell1 = new BitSet(25);
        barbell1.set(1);
        barbell1.set(2);
        barbell1.set(3);
        barbell1.set(7);
        barbell1.set(12);
        barbell1.set(17);
        barbell1.set(21);
        barbell1.set(22);
        barbell1.set(23);

        barbell2 = new BitSet(25);
        barbell2.set(2);
        barbell2.set(3);
        barbell2.set(4);
        barbell2.set(8);
        barbell2.set(13);
        barbell2.set(18);
        barbell2.set(22);
        barbell2.set(23);
        barbell2.set(24);

        blockofsix0 = new BitSet(25);
        blockofsix0.set(0);
        blockofsix0.set(1);
        blockofsix0.set(2);
        blockofsix0.set(5);
        blockofsix0.set(6);
        blockofsix0.set(7);

        blockofsix1 = new BitSet(25);
        blockofsix1.set(1);
        blockofsix1.set(2);
        blockofsix1.set(3);
        blockofsix1.set(6);
        blockofsix1.set(7);
        blockofsix1.set(8);

        blockofsix2 = new BitSet(25);
        blockofsix2.set(2);
        blockofsix2.set(3);
        blockofsix2.set(4);
        blockofsix2.set(7);
        blockofsix2.set(8);
        blockofsix2.set(9);

        blockofsix3 = new BitSet(25);
        blockofsix3.set(5);
        blockofsix3.set(6);
        blockofsix3.set(7);
        blockofsix3.set(10);
        blockofsix3.set(11);
        blockofsix3.set(12);

        blockofsix4 = new BitSet(25);
        blockofsix4.set(6);
        blockofsix4.set(7);
        blockofsix4.set(8);
        blockofsix4.set(11);
        blockofsix4.set(12);
        blockofsix4.set(13);

        blockofsix5 = new BitSet(25);
        blockofsix5.set(7);
        blockofsix5.set(8);
        blockofsix5.set(9);
        blockofsix5.set(12);
        blockofsix5.set(13);
        blockofsix5.set(14);

        blockofsix6 = new BitSet(25);
        blockofsix6.set(10);
        blockofsix6.set(11);
        blockofsix6.set(12);
        blockofsix6.set(15);
        blockofsix6.set(16);
        blockofsix6.set(17);

        blockofsix7 = new BitSet(25);
        blockofsix7.set(11);
        blockofsix7.set(12);
        blockofsix7.set(13);
        blockofsix7.set(16);
        blockofsix7.set(17);
        blockofsix7.set(18);

        blockofsix8 = new BitSet(25);
        blockofsix8.set(12);
        blockofsix8.set(13);
        blockofsix8.set(14);
        blockofsix8.set(17);
        blockofsix8.set(18);
        blockofsix8.set(19);

        blockofsix9 = new BitSet(25);
        blockofsix9.set(15);
        blockofsix9.set(16);
        blockofsix9.set(17);
        blockofsix9.set(20);
        blockofsix9.set(21);
        blockofsix9.set(22);

        blockofsix10 = new BitSet(25);
        blockofsix10.set(16);
        blockofsix10.set(17);
        blockofsix10.set(18);
        blockofsix10.set(21);
        blockofsix10.set(22);
        blockofsix10.set(23);

        blockofsix11 = new BitSet(25);
        blockofsix11.set(17);
        blockofsix11.set(18);
        blockofsix11.set(19);
        blockofsix11.set(22);
        blockofsix11.set(23);
        blockofsix11.set(24);

        blockofsix12 = new BitSet(25);
        blockofsix12.set(0);
        blockofsix12.set(1);
        blockofsix12.set(5);
        blockofsix12.set(6);
        blockofsix12.set(10);
        blockofsix12.set(11);

        blockofsix13 = new BitSet(25);
        blockofsix13.set(1);
        blockofsix13.set(2);
        blockofsix13.set(6);
        blockofsix13.set(7);
        blockofsix13.set(11);
        blockofsix13.set(12);

        blockofsix14 = new BitSet(25);
        blockofsix14.set(2);
        blockofsix14.set(3);
        blockofsix14.set(7);
        blockofsix14.set(8);
        blockofsix14.set(12);
        blockofsix14.set(13);

        blockofsix15 = new BitSet(25);
        blockofsix15.set(3);
        blockofsix15.set(4);
        blockofsix15.set(8);
        blockofsix15.set(9);
        blockofsix15.set(13);
        blockofsix15.set(14);

        blockofsix16 = new BitSet(25);
        blockofsix16.set(5);
        blockofsix16.set(6);
        blockofsix16.set(10);
        blockofsix16.set(11);
        blockofsix16.set(15);
        blockofsix16.set(16);

        blockofsix17 = new BitSet(25);
        blockofsix17.set(6);
        blockofsix17.set(7);
        blockofsix17.set(11);
        blockofsix17.set(12);
        blockofsix17.set(16);
        blockofsix17.set(17);

        blockofsix18 = new BitSet(25);
        blockofsix18.set(7);
        blockofsix18.set(8);
        blockofsix18.set(12);
        blockofsix18.set(13);
        blockofsix18.set(17);
        blockofsix18.set(18);

        blockofsix19 = new BitSet(25);
        blockofsix19.set(8);
        blockofsix19.set(9);
        blockofsix19.set(13);
        blockofsix19.set(14);
        blockofsix19.set(18);
        blockofsix19.set(19);

        blockofsix20 = new BitSet(25);
        blockofsix20.set(10);
        blockofsix20.set(11);
        blockofsix20.set(15);
        blockofsix20.set(16);
        blockofsix20.set(20);
        blockofsix20.set(21);

        blockofsix21 = new BitSet(25);
        blockofsix21.set(11);
        blockofsix21.set(12);
        blockofsix21.set(16);
        blockofsix21.set(17);
        blockofsix21.set(21);
        blockofsix21.set(22);

        blockofsix22 = new BitSet(25);
        blockofsix22.set(12);
        blockofsix22.set(13);
        blockofsix22.set(17);
        blockofsix22.set(18);
        blockofsix22.set(22);
        blockofsix22.set(23);

        blockofsix23 = new BitSet(25);
        blockofsix23.set(13);
        blockofsix23.set(14);
        blockofsix23.set(18);
        blockofsix23.set(19);
        blockofsix23.set(23);
        blockofsix23.set(24);

        blockofeight0 = new BitSet(25);
        blockofeight0.set(0);
        blockofeight0.set(1);
        blockofeight0.set(2);
        blockofeight0.set(3);
        blockofeight0.set(5);
        blockofeight0.set(6);
        blockofeight0.set(7);
        blockofeight0.set(8);

        blockofeight1 = new BitSet(25);
        blockofeight1.set(1);
        blockofeight1.set(2);
        blockofeight1.set(3);
        blockofeight1.set(4);
        blockofeight1.set(6);
        blockofeight1.set(7);
        blockofeight1.set(8);
        blockofeight1.set(9);

        blockofeight2 = new BitSet(25);
        blockofeight2.set(5);
        blockofeight2.set(6);
        blockofeight2.set(7);
        blockofeight2.set(8);
        blockofeight2.set(10);
        blockofeight2.set(11);
        blockofeight2.set(12);
        blockofeight2.set(13);

        blockofeight3 = new BitSet(25);
        blockofeight3.set(6);
        blockofeight3.set(7);
        blockofeight3.set(8);
        blockofeight3.set(9);
        blockofeight3.set(11);
        blockofeight3.set(12);
        blockofeight3.set(13);
        blockofeight3.set(14);

        blockofeight4 = new BitSet(25);
        blockofeight4.set(10);
        blockofeight4.set(11);
        blockofeight4.set(12);
        blockofeight4.set(13);
        blockofeight4.set(15);
        blockofeight4.set(16);
        blockofeight4.set(17);
        blockofeight4.set(18);

        blockofeight5 = new BitSet(25);
        blockofeight5.set(11);
        blockofeight5.set(12);
        blockofeight5.set(13);
        blockofeight5.set(14);
        blockofeight5.set(16);
        blockofeight5.set(17);
        blockofeight5.set(18);
        blockofeight5.set(19);

        blockofeight6 = new BitSet(25);
        blockofeight6.set(15);
        blockofeight6.set(16);
        blockofeight6.set(17);
        blockofeight6.set(18);
        blockofeight6.set(20);
        blockofeight6.set(21);
        blockofeight6.set(22);
        blockofeight6.set(23);

        blockofeight7 = new BitSet(25);
        blockofeight7.set(16);
        blockofeight7.set(17);
        blockofeight7.set(18);
        blockofeight7.set(19);
        blockofeight7.set(21);
        blockofeight7.set(22);
        blockofeight7.set(23);
        blockofeight7.set(24);

        blockofeight8 = new BitSet(25);
        blockofeight8.set(0);
        blockofeight8.set(1);
        blockofeight8.set(5);
        blockofeight8.set(6);
        blockofeight8.set(10);
        blockofeight8.set(11);
        blockofeight8.set(15);
        blockofeight8.set(16);

        blockofeight9 = new BitSet(25);
        blockofeight9.set(1);
        blockofeight9.set(2);
        blockofeight9.set(6);
        blockofeight9.set(7);
        blockofeight9.set(11);
        blockofeight9.set(12);
        blockofeight9.set(16);
        blockofeight9.set(17);

        blockofeight10 = new BitSet(25);
        blockofeight10.set(2);
        blockofeight10.set(3);
        blockofeight10.set(7);
        blockofeight10.set(8);
        blockofeight10.set(12);
        blockofeight10.set(13);
        blockofeight10.set(17);
        blockofeight10.set(18);

        blockofeight11 = new BitSet(25);
        blockofeight11.set(3);
        blockofeight11.set(4);
        blockofeight11.set(8);
        blockofeight11.set(9);
        blockofeight11.set(13);
        blockofeight11.set(14);
        blockofeight11.set(18);
        blockofeight11.set(19);

        blockofeight12 = new BitSet(25);
        blockofeight12.set(5);
        blockofeight12.set(6);
        blockofeight12.set(10);
        blockofeight12.set(11);
        blockofeight12.set(15);
        blockofeight12.set(16);
        blockofeight12.set(20);
        blockofeight12.set(21);

        blockofeight13 = new BitSet(25);
        blockofeight13.set(6);
        blockofeight13.set(7);
        blockofeight13.set(11);
        blockofeight13.set(12);
        blockofeight13.set(16);
        blockofeight13.set(17);
        blockofeight13.set(21);
        blockofeight13.set(22);

        blockofeight14 = new BitSet(25);
        blockofeight14.set(7);
        blockofeight14.set(8);
        blockofeight14.set(12);
        blockofeight14.set(13);
        blockofeight14.set(17);
        blockofeight14.set(18);
        blockofeight14.set(22);
        blockofeight14.set(23);

        blockofeight15 = new BitSet(25);
        blockofeight15.set(8);
        blockofeight15.set(9);
        blockofeight15.set(13);
        blockofeight15.set(14);
        blockofeight15.set(18);
        blockofeight15.set(19);
        blockofeight15.set(23);
        blockofeight15.set(24);

        blockofnine0 = new BitSet(25);
        blockofnine0.set(0);
        blockofnine0.set(1);
        blockofnine0.set(2);
        blockofnine0.set(5);
        blockofnine0.set(6);
        blockofnine0.set(7);
        blockofnine0.set(10);
        blockofnine0.set(11);
        blockofnine0.set(12);

        blockofnine1 = new BitSet(25);
        blockofnine1.set(1);
        blockofnine1.set(2);
        blockofnine1.set(3);
        blockofnine1.set(6);
        blockofnine1.set(7);
        blockofnine1.set(8);
        blockofnine1.set(11);
        blockofnine1.set(12);
        blockofnine1.set(13);

        blockofnine2 = new BitSet(25);
        blockofnine2.set(2);
        blockofnine2.set(3);
        blockofnine2.set(4);
        blockofnine2.set(7);
        blockofnine2.set(8);
        blockofnine2.set(9);
        blockofnine2.set(12);
        blockofnine2.set(13);
        blockofnine2.set(14);

        blockofnine3 = new BitSet(25);
        blockofnine3.set(5);
        blockofnine3.set(6);
        blockofnine3.set(7);
        blockofnine3.set(10);
        blockofnine3.set(11);
        blockofnine3.set(12);
        blockofnine3.set(15);
        blockofnine3.set(16);
        blockofnine3.set(17);

        blockofnine4 = new BitSet(25);
        blockofnine4.set(6);
        blockofnine4.set(7);
        blockofnine4.set(8);
        blockofnine4.set(11);
        blockofnine4.set(12);
        blockofnine4.set(13);
        blockofnine4.set(16);
        blockofnine4.set(17);
        blockofnine4.set(18);

        blockofnine5 = new BitSet(25);
        blockofnine5.set(7);
        blockofnine5.set(8);
        blockofnine5.set(9);
        blockofnine5.set(12);
        blockofnine5.set(13);
        blockofnine5.set(14);
        blockofnine5.set(17);
        blockofnine5.set(18);
        blockofnine5.set(19);

        blockofnine6 = new BitSet(25);
        blockofnine6.set(10);
        blockofnine6.set(11);
        blockofnine6.set(12);
        blockofnine6.set(15);
        blockofnine6.set(16);
        blockofnine6.set(17);
        blockofnine6.set(20);
        blockofnine6.set(21);
        blockofnine6.set(22);

        blockofnine7 = new BitSet(25);
        blockofnine7.set(11);
        blockofnine7.set(12);
        blockofnine7.set(13);
        blockofnine7.set(16);
        blockofnine7.set(17);
        blockofnine7.set(18);
        blockofnine7.set(21);
        blockofnine7.set(22);
        blockofnine7.set(23);

        blockofnine8 = new BitSet(25);
        blockofnine8.set(12);
        blockofnine8.set(13);
        blockofnine8.set(14);
        blockofnine8.set(17);
        blockofnine8.set(18);
        blockofnine8.set(19);
        blockofnine8.set(22);
        blockofnine8.set(23);
        blockofnine8.set(24);

        bowtieone0 = new BitSet(25);
        bowtieone0.set(0);
        bowtieone0.set(1);
        bowtieone0.set(2);
        bowtieone0.set(3);
        bowtieone0.set(4);
        bowtieone0.set(6);
        bowtieone0.set(7);
        bowtieone0.set(8);
        bowtieone0.set(12);
        bowtieone0.set(16);
        bowtieone0.set(17);
        bowtieone0.set(18);
        bowtieone0.set(20);
        bowtieone0.set(21);
        bowtieone0.set(22);
        bowtieone0.set(23);
        bowtieone0.set(24);

        bowtietwo0 = new BitSet(25);
        bowtietwo0.set(0);
        bowtietwo0.set(1);
        bowtietwo0.set(5);
        bowtietwo0.set(6);
        bowtietwo0.set(12);
        bowtietwo0.set(18);
        bowtietwo0.set(19);
        bowtietwo0.set(23);
        bowtietwo0.set(24);

        bowtietwo1 = new BitSet(25);
        bowtietwo1.set(3);
        bowtietwo1.set(4);
        bowtietwo1.set(8);
        bowtietwo1.set(9);
        bowtietwo1.set(12);
        bowtietwo1.set(15);
        bowtietwo1.set(16);
        bowtietwo1.set(20);
        bowtietwo1.set(21);

        cents0 = new BitSet(25);
        cents0.set(1);
        cents0.set(2);
        cents0.set(3);
        cents0.set(5);
        cents0.set(6);
        cents0.set(8);
        cents0.set(9);
        cents0.set(11);
        cents0.set(13);

        cents1 = new BitSet(25);
        cents1.set(6);
        cents1.set(7);
        cents1.set(8);
        cents1.set(10);
        cents1.set(11);
        cents1.set(13);
        cents1.set(14);
        cents1.set(16);
        cents1.set(18);

        cents2 = new BitSet(25);
        cents2.set(11);
        cents2.set(12);
        cents2.set(13);
        cents2.set(15);
        cents2.set(16);
        cents2.set(18);
        cents2.set(19);
        cents2.set(21);
        cents2.set(23);

        champagneglassempty0 = new BitSet(25);
        champagneglassempty0.set(0);
        champagneglassempty0.set(6);
        champagneglassempty0.set(9);
        champagneglassempty0.set(12);
        champagneglassempty0.set(13);
        champagneglassempty0.set(14);
        champagneglassempty0.set(16);
        champagneglassempty0.set(19);
        champagneglassempty0.set(20);

        champagneglassfull0 = new BitSet(25);
        champagneglassfull0.set(0);
        champagneglassfull0.set(5);
        champagneglassfull0.set(6);
        champagneglassfull0.set(9);
        champagneglassfull0.set(10);
        champagneglassfull0.set(11);
        champagneglassfull0.set(12);
        champagneglassfull0.set(13);
        champagneglassfull0.set(14);
        champagneglassfull0.set(15);
        champagneglassfull0.set(16);
        champagneglassfull0.set(19);
        champagneglassfull0.set(20);

        checkmark0 = new BitSet(25);
        checkmark0.set(2);
        checkmark0.set(3);
        checkmark0.set(4);
        checkmark0.set(8);
        checkmark0.set(12);
        checkmark0.set(16);
        checkmark0.set(20);

        checkmark1 = new BitSet(25);
        checkmark1.set(0);
        checkmark1.set(6);
        checkmark1.set(12);
        checkmark1.set(18);
        checkmark1.set(22);
        checkmark1.set(23);
        checkmark1.set(24);

        diamond0 = new BitSet(25);
        diamond0.set(2);
        diamond0.set(6);
        diamond0.set(7);
        diamond0.set(8);
        diamond0.set(10);
        diamond0.set(11);
        diamond0.set(12);
        diamond0.set(13);
        diamond0.set(14);
        diamond0.set(16);
        diamond0.set(17);
        diamond0.set(18);
        diamond0.set(22);

        diamondinside0 = new BitSet(25);
        diamondinside0.set(7);
        diamondinside0.set(11);
        diamondinside0.set(12);
        diamondinside0.set(13);
        diamondinside0.set(17);

        diamondoutline0 = new BitSet(25);
        diamondoutline0.set(2);
        diamondoutline0.set(6);
        diamondoutline0.set(8);
        diamondoutline0.set(10);
        diamondoutline0.set(14);
        diamondoutline0.set(16);
        diamondoutline0.set(18);
        diamondoutline0.set(22);

        dollarsign0 = new BitSet(25);
        dollarsign0.set(0);
        dollarsign0.set(1);
        dollarsign0.set(2);
        dollarsign0.set(4);
        dollarsign0.set(5);
        dollarsign0.set(7);
        dollarsign0.set(9);
        dollarsign0.set(10);
        dollarsign0.set(11);
        dollarsign0.set(12);
        dollarsign0.set(13);
        dollarsign0.set(14);
        dollarsign0.set(15);
        dollarsign0.set(17);
        dollarsign0.set(19);
        dollarsign0.set(20);
        dollarsign0.set(22);
        dollarsign0.set(24);

        fieldgoal0 = new BitSet(25);
        fieldgoal0.set(0);
        fieldgoal0.set(1);
        fieldgoal0.set(2);
        fieldgoal0.set(7);
        fieldgoal0.set(10);
        fieldgoal0.set(12);
        fieldgoal0.set(13);
        fieldgoal0.set(14);
        fieldgoal0.set(17);
        fieldgoal0.set(20);
        fieldgoal0.set(21);
        fieldgoal0.set(22);

        fourcornersbig0 = new BitSet(25);
        fourcornersbig0.set(0);
        fourcornersbig0.set(4);
        fourcornersbig0.set(20);
        fourcornersbig0.set(24);

        fourcornerssmall0 = new BitSet(25);
        fourcornerssmall0.set(6);
        fourcornerssmall0.set(8);
        fourcornerssmall0.set(16);
        fourcornerssmall0.set(18);

        frameinside0 = new BitSet(25);
        frameinside0.set(6);
        frameinside0.set(7);
        frameinside0.set(8);
        frameinside0.set(11);
        frameinside0.set(13);
        frameinside0.set(16);
        frameinside0.set(17);
        frameinside0.set(18);

        kite0 = new BitSet(25);
        kite0.set(4);
        kite0.set(8);
        kite0.set(12);
        kite0.set(15);
        kite0.set(16);
        kite0.set(20);
        kite0.set(21);

        kite1 = new BitSet(25);
        kite1.set(0);
        kite1.set(1);
        kite1.set(5);
        kite1.set(6);
        kite1.set(12);
        kite1.set(18);
        kite1.set(24);

        ladder0 = new BitSet(25);
        ladder0.set(0);
        ladder0.set(1);
        ladder0.set(2);
        ladder0.set(3);
        ladder0.set(4);
        ladder0.set(6);
        ladder0.set(8);
        ladder0.set(10);
        ladder0.set(11);
        ladder0.set(12);
        ladder0.set(13);
        ladder0.set(14);

        ladder1 = new BitSet(25);
        ladder1.set(5);
        ladder1.set(6);
        ladder1.set(7);
        ladder1.set(8);
        ladder1.set(9);
        ladder1.set(11);
        ladder1.set(13);
        ladder1.set(15);
        ladder1.set(16);
        ladder1.set(17);
        ladder1.set(18);
        ladder1.set(19);

        ladder2 = new BitSet(25);
        ladder2.set(10);
        ladder2.set(11);
        ladder2.set(12);
        ladder2.set(13);
        ladder2.set(14);
        ladder2.set(16);
        ladder2.set(18);
        ladder2.set(20);
        ladder2.set(21);
        ladder2.set(22);
        ladder2.set(23);
        ladder2.set(24);

        letterm0 = new BitSet(25);
        letterm0.set(0);
        letterm0.set(1);
        letterm0.set(2);
        letterm0.set(3);
        letterm0.set(4);
        letterm0.set(6);
        letterm0.set(12);
        letterm0.set(16);
        letterm0.set(20);
        letterm0.set(21);
        letterm0.set(22);
        letterm0.set(23);
        letterm0.set(24);

        letterp0 = new BitSet(25);
        letterp0.set(0);
        letterp0.set(1);
        letterp0.set(2);
        letterp0.set(3);
        letterp0.set(4);
        letterp0.set(5);
        letterp0.set(7);
        letterp0.set(10);
        letterp0.set(12);
        letterp0.set(15);
        letterp0.set(17);
        letterp0.set(20);
        letterp0.set(21);
        letterp0.set(22);

        letters0 = new BitSet(25);
        letters0.set(0);
        letters0.set(1);
        letters0.set(2);
        letters0.set(4);
        letters0.set(5);
        letters0.set(7);
        letters0.set(9);
        letters0.set(10);
        letters0.set(14);
        letters0.set(15);
        letters0.set(17);
        letters0.set(19);
        letters0.set(15);
        letters0.set(20);
        letters0.set(22);
        letters0.set(23);
        letters0.set(24);

        letterw0 = new BitSet(25);
        letterw0.set(0);
        letterw0.set(1);
        letterw0.set(2);
        letterw0.set(3);
        letterw0.set(4);
        letterw0.set(8);
        letterw0.set(12);
        letterw0.set(18);
        letterw0.set(20);
        letterw0.set(21);
        letterw0.set(22);
        letterw0.set(23);
        letterw0.set(24);

        lettery0 = new BitSet(25);
        lettery0.set(0);
        lettery0.set(6);
        lettery0.set(12);
        lettery0.set(13);
        lettery0.set(14);
        lettery0.set(16);
        lettery0.set(20);

        loveletter0 = new BitSet(25);
        loveletter0.set(0);
        loveletter0.set(1);
        loveletter0.set(2);
        loveletter0.set(3);
        loveletter0.set(4);
        loveletter0.set(9);
        loveletter0.set(14);
        loveletter0.set(15);
        loveletter0.set(16);
        loveletter0.set(19);
        loveletter0.set(20);
        loveletter0.set(21);
        loveletter0.set(24);

        percentsign0 = new BitSet(25);
        percentsign0.set(0);
        percentsign0.set(1);
        percentsign0.set(4);
        percentsign0.set(5);
        percentsign0.set(6);
        percentsign0.set(8);
        percentsign0.set(12);
        percentsign0.set(16);
        percentsign0.set(18);
        percentsign0.set(19);
        percentsign0.set(20);
        percentsign0.set(23);
        percentsign0.set(24);

        pyramid0 = new BitSet(25);
        pyramid0.set(4);
        pyramid0.set(8);
        pyramid0.set(9);
        pyramid0.set(12);
        pyramid0.set(13);
        pyramid0.set(14);
        pyramid0.set(18);
        pyramid0.set(19);
        pyramid0.set(24);

        sputnik0 = new BitSet(25);
        sputnik0.set(0);
        sputnik0.set(4);
        sputnik0.set(6);
        sputnik0.set(7);
        sputnik0.set(8);
        sputnik0.set(11);
        sputnik0.set(12);
        sputnik0.set(13);
        sputnik0.set(16);
        sputnik0.set(17);
        sputnik0.set(18);
        sputnik0.set(20);
        sputnik0.set(24);

        stampone0 = new BitSet(25);
        stampone0.set(0);
        stampone0.set(1);
        stampone0.set(5);
        stampone0.set(6);

        stampone1 = new BitSet(25);
        stampone1.set(3);
        stampone1.set(4);
        stampone1.set(8);
        stampone1.set(9);

        stampone2 = new BitSet(25);
        stampone2.set(15);
        stampone2.set(16);
        stampone2.set(20);
        stampone2.set(21);

        stampone3 = new BitSet(25);
        stampone3.set(18);
        stampone3.set(19);
        stampone3.set(23);
        stampone3.set(24);

        stamptwo0 = new BitSet(25);
        stamptwo0.set(0);
        stamptwo0.set(1);
        stamptwo0.set(5);
        stamptwo0.set(6);
        stamptwo0.set(15);
        stamptwo0.set(16);
        stamptwo0.set(20);
        stamptwo0.set(21);

        stamptwo1 = new BitSet(25);
        stamptwo1.set(0);
        stamptwo1.set(1);
        stamptwo1.set(3);
        stamptwo1.set(4);
        stamptwo1.set(5);
        stamptwo1.set(6);
        stamptwo1.set(8);
        stamptwo1.set(9);

        stamptwo2 = new BitSet(25);
        stamptwo2.set(3);
        stamptwo2.set(4);
        stamptwo2.set(8);
        stamptwo2.set(9);
        stamptwo2.set(18);
        stamptwo2.set(19);
        stamptwo2.set(23);
        stamptwo2.set(24);

        stamptwo3 = new BitSet(25);
        stamptwo3.set(15);
        stamptwo3.set(16);
        stamptwo3.set(18);
        stamptwo3.set(19);
        stamptwo3.set(20);
        stamptwo3.set(21);
        stamptwo3.set(23);
        stamptwo3.set(24);

        stamptwo4 = new BitSet(25);
        stamptwo4.set(0);
        stamptwo4.set(1);
        stamptwo4.set(5);
        stamptwo4.set(6);
        stamptwo4.set(18);
        stamptwo4.set(19);
        stamptwo4.set(23);
        stamptwo4.set(24);

        stamptwo5 = new BitSet(25);
        stamptwo5.set(3);
        stamptwo5.set(4);
        stamptwo5.set(8);
        stamptwo5.set(9);
        stamptwo5.set(15);
        stamptwo5.set(16);
        stamptwo5.set(20);
        stamptwo5.set(21);

        stampfour0 = new BitSet(25);
        stampfour0.set(0);
        stampfour0.set(1);
        stampfour0.set(3);
        stampfour0.set(4);
        stampfour0.set(5);
        stampfour0.set(6);
        stampfour0.set(8);
        stampfour0.set(9);
        stampfour0.set(15);
        stampfour0.set(16);
        stampfour0.set(18);
        stampfour0.set(19);
        stampfour0.set(20);
        stampfour0.set(21);
        stampfour0.set(23);
        stampfour0.set(24);

        stampand3corners0 = new BitSet(25);
        stampand3corners0.set(0);
        stampand3corners0.set(1);
        stampand3corners0.set(4);
        stampand3corners0.set(5);
        stampand3corners0.set(6);
        stampand3corners0.set(20);
        stampand3corners0.set(24);

        stampand3corners1 = new BitSet(25);
        stampand3corners1.set(0);
        stampand3corners1.set(3);
        stampand3corners1.set(4);
        stampand3corners1.set(8);
        stampand3corners1.set(9);
        stampand3corners1.set(20);
        stampand3corners1.set(24);

        stampand3corners2 = new BitSet(25);
        stampand3corners2.set(0);
        stampand3corners2.set(4);
        stampand3corners2.set(15);
        stampand3corners2.set(16);
        stampand3corners2.set(20);
        stampand3corners2.set(21);
        stampand3corners2.set(24);

        stampand3corners3 = new BitSet(25);
        stampand3corners3.set(0);
        stampand3corners3.set(4);
        stampand3corners3.set(18);
        stampand3corners3.set(19);
        stampand3corners3.set(20);
        stampand3corners3.set(23);
        stampand3corners3.set(24);

        stampandline0 = new BitSet(25);
        stampandline0.set(0);
        stampandline0.set(1);
        stampandline0.set(4);
        stampandline0.set(5);
        stampandline0.set(6);
        stampandline0.set(8);
        stampandline0.set(12);
        stampandline0.set(16);
        stampandline0.set(20);

        stampandline1 = new BitSet(25);
        stampandline1.set(0);
        stampandline1.set(3);
        stampandline1.set(4);
        stampandline1.set(6);
        stampandline1.set(8);
        stampandline1.set(9);
        stampandline1.set(12);
        stampandline1.set(18);
        stampandline1.set(24);

        stampandline2 = new BitSet(25);
        stampandline2.set(4);
        stampandline2.set(8);
        stampandline2.set(12);
        stampandline2.set(16);
        stampandline2.set(18);
        stampandline2.set(19);
        stampandline2.set(20);
        stampandline2.set(23);
        stampandline2.set(24);

        stampandline3 = new BitSet(25);
        stampandline3.set(0);
        stampandline3.set(6);
        stampandline3.set(12);
        stampandline3.set(15);
        stampandline3.set(16);
        stampandline3.set(18);
        stampandline3.set(20);
        stampandline3.set(21);
        stampandline3.set(24);

        straightandcorners0 = new BitSet(25);
        straightandcorners0.set(0);
        straightandcorners0.set(4);
        straightandcorners0.set(6);
        straightandcorners0.set(12);
        straightandcorners0.set(18);
        straightandcorners0.set(20);
        straightandcorners0.set(24);

        straightandcorners1 = new BitSet(25);
        straightandcorners1.set(0);
        straightandcorners1.set(4);
        straightandcorners1.set(8);
        straightandcorners1.set(12);
        straightandcorners1.set(16);
        straightandcorners1.set(20);
        straightandcorners1.set(24);

        tophat0 = new BitSet(25);
        tophat0.set(4);
        tophat0.set(6);
        tophat0.set(7);
        tophat0.set(8);
        tophat0.set(9);
        tophat0.set(11);
        tophat0.set(12);
        tophat0.set(13);
        tophat0.set(14);
        tophat0.set(16);
        tophat0.set(17);
        tophat0.set(18);
        tophat0.set(19);
        tophat0.set(24);

        tree0 = new BitSet(25);
        tree0.set(2);
        tree0.set(6);
        tree0.set(7);
        tree0.set(10);
        tree0.set(11);
        tree0.set(12);
        tree0.set(13);
        tree0.set(14);
        tree0.set(16);
        tree0.set(17);
        tree0.set(22);

        turtle0 = new BitSet(25);
        turtle0.set(1);
        turtle0.set(4);
        turtle0.set(6);
        turtle0.set(7);
        turtle0.set(8);
        turtle0.set(10);
        turtle0.set(11);
        turtle0.set(12);
        turtle0.set(13);
        turtle0.set(16);
        turtle0.set(17);
        turtle0.set(18);
        turtle0.set(21);
        turtle0.set(24);

        turtle1 = new BitSet(25);
        turtle1.set(2);
        turtle1.set(5);
        turtle1.set(6);
        turtle1.set(7);
        turtle1.set(8);
        turtle1.set(9);
        turtle1.set(11);
        turtle1.set(12);
        turtle1.set(13);
        turtle1.set(16);
        turtle1.set(17);
        turtle1.set(18);
        turtle1.set(20);
        turtle1.set(24);

        turtle2 = new BitSet(25);
        turtle2.set(0);
        turtle2.set(3);
        turtle2.set(6);
        turtle2.set(7);
        turtle2.set(8);
        turtle2.set(11);
        turtle2.set(12);
        turtle2.set(13);
        turtle2.set(14);
        turtle2.set(16);
        turtle2.set(17);
        turtle2.set(18);
        turtle2.set(20);
        turtle2.set(23);

        turtle3 = new BitSet(25);
        turtle3.set(0);
        turtle3.set(4);
        turtle3.set(6);
        turtle3.set(7);
        turtle3.set(8);
        turtle3.set(11);
        turtle3.set(12);
        turtle3.set(13);
        turtle3.set(15);
        turtle3.set(16);
        turtle3.set(17);
        turtle3.set(18);
        turtle3.set(19);
        turtle3.set(22);
    }

    private BitSet and(final BitSet bitSet1, final BitSet bitSet2) {
        BitSet lBitSet = (BitSet)bitSet1.clone();
        lBitSet.and(bitSet2);
        return lBitSet;
    }
    
    //! A method which returns true for a winning card.
    /*!
      A method which returns true if the card pattern bits match the winning
      pattern.
    */
    public boolean winner(BitSet cardBits, String pattern){
        switch (pattern) {
            case "AnyDiagonalRowOrColumn":
                return (row0.equals(and(cardBits, row0)) |
                        row1.equals(and(cardBits, row1)) |
                        row2.equals(and(cardBits, row2)) |
                        row3.equals(and(cardBits, row3)) |
                        row4.equals(and(cardBits, row4)) |
                        col0.equals(and(cardBits, col0)) |
                        col1.equals(and(cardBits, col1)) |
                        col2.equals(and(cardBits, col2)) |
                        col3.equals(and(cardBits, col3)) |
                        col4.equals(and(cardBits, col4)) |
                        downdiagonal0.equals(and(cardBits, downdiagonal0)) |
                        updiagonal0.equals(and(cardBits, updiagonal0)));
            case "Airplane":
                return (airplane0.equals(and(cardBits, airplane0)) |
                        airplane1.equals(and(cardBits, airplane1)) |
                        airplane2.equals(and(cardBits, airplane2)) |
                        airplane3.equals(and(cardBits, airplane3)));
            case "Arrowhead":
                return (arrowhead0.equals(and(cardBits, arrowhead0)) |
                        arrowhead1.equals(and(cardBits, arrowhead1)) |
                        arrowhead2.equals(and(cardBits, arrowhead2)) |
                        arrowhead3.equals(and(cardBits, arrowhead3)));
            case "BAndOBingo":
                return (col0.equals(and(cardBits, col0)) &
                        col4.equals(and(cardBits, col4)));
            case "Barbell":
                return (barbell0.equals(and(cardBits, barbell0)) |
                        barbell1.equals(and(cardBits, barbell1)) |
                        barbell2.equals(and(cardBits, barbell2)));
            case "BlockOf6":
                return (blockofsix0.equals(and(cardBits, blockofsix0)) ||
                        blockofsix1.equals(and(cardBits, blockofsix1)) ||
                        blockofsix2.equals(and(cardBits, blockofsix2)) ||
                        blockofsix3.equals(and(cardBits, blockofsix3)) ||
                        blockofsix4.equals(and(cardBits, blockofsix4)) ||
                        blockofsix5.equals(and(cardBits, blockofsix5)) ||
                        blockofsix6.equals(and(cardBits, blockofsix6)) ||
                        blockofsix7.equals(and(cardBits, blockofsix7)) ||
                        blockofsix8.equals(and(cardBits, blockofsix8)) ||
                        blockofsix9.equals(and(cardBits, blockofsix9)) ||
                        blockofsix10.equals(and(cardBits, blockofsix10)) ||
                        blockofsix11.equals(and(cardBits, blockofsix11)) ||
                        blockofsix12.equals(and(cardBits, blockofsix12)) ||
                        blockofsix13.equals(and(cardBits, blockofsix13)) ||
                        blockofsix14.equals(and(cardBits, blockofsix14)) ||
                        blockofsix15.equals(and(cardBits, blockofsix15)) ||
                        blockofsix16.equals(and(cardBits, blockofsix16)) ||
                        blockofsix17.equals(and(cardBits, blockofsix17)) ||
                        blockofsix18.equals(and(cardBits, blockofsix18)) ||
                        blockofsix19.equals(and(cardBits, blockofsix19)) ||
                        blockofsix20.equals(and(cardBits, blockofsix20)) ||
                        blockofsix21.equals(and(cardBits, blockofsix21)) ||
                        blockofsix22.equals(and(cardBits, blockofsix22)) ||
                        blockofsix23.equals(and(cardBits, blockofsix23)));
            case "BlockOf8":
                return (blockofeight0.equals(and(cardBits, blockofeight0)) ||
                        blockofeight1.equals(and(cardBits, blockofeight1)) ||
                        blockofeight2.equals(and(cardBits, blockofeight2)) ||
                        blockofeight3.equals(and(cardBits, blockofeight3)) ||
                        blockofeight4.equals(and(cardBits, blockofeight4)) ||
                        blockofeight5.equals(and(cardBits, blockofeight5)) ||
                        blockofeight6.equals(and(cardBits, blockofeight6)) ||
                        blockofeight7.equals(and(cardBits, blockofeight7)) ||
                        blockofeight8.equals(and(cardBits, blockofeight8)) ||
                        blockofeight9.equals(and(cardBits, blockofeight9)) ||
                        blockofeight10.equals(and(cardBits, blockofeight10)) ||
                        blockofeight11.equals(and(cardBits, blockofeight11)) ||
                        blockofeight12.equals(and(cardBits, blockofeight12)) ||
                        blockofeight13.equals(and(cardBits, blockofeight13)) ||
                        blockofeight14.equals(and(cardBits, blockofeight14)) ||
                        blockofeight15.equals(and(cardBits, blockofeight15)));
            case "BlockOf9":
                return (blockofnine0.equals(and(cardBits, blockofnine0)) ||
                        blockofnine1.equals(and(cardBits, blockofnine1)) ||
                        blockofnine2.equals(and(cardBits, blockofnine2)) ||
                        blockofnine3.equals(and(cardBits, blockofnine3)) ||
                        blockofnine4.equals(and(cardBits, blockofnine4)) ||
                        blockofnine5.equals(and(cardBits, blockofnine5)) ||
                        blockofnine6.equals(and(cardBits, blockofnine6)) ||
                        blockofnine7.equals(and(cardBits, blockofnine7)) ||
                        blockofnine8.equals(and(cardBits, blockofnine8)));
            case "BowTie1":
                return (bowtieone0.equals(and(cardBits, bowtieone0)));
            case "BowTie2":
                return (bowtietwo0.equals(and(cardBits, bowtietwo0)) ||
                        bowtietwo1.equals(and(cardBits, bowtietwo1)));
            case "Cents":
                return (cents0.equals(and(cardBits, cents0)) ||
                        cents1.equals(and(cardBits, cents1)) ||
                        cents2.equals(and(cardBits, cents2)));
            case "ChampagneGlassEmpty":
                return (champagneglassempty0.equals(and(cardBits, champagneglassempty0)));
            case "ChampagneGlassFull":
                return (champagneglassfull0.equals(and(cardBits, champagneglassfull0)));
            case "Checkmark":
                return (checkmark0.equals(and(cardBits, checkmark0)) ||
                        checkmark1.equals(and(cardBits, checkmark1)));
            case "Column":
                return (col0.equals(and(cardBits, col0)) ||
                        col1.equals(and(cardBits, col1)) ||
                        col2.equals(and(cardBits, col2)) ||
                        col3.equals(and(cardBits, col3)) ||
                        col4.equals(and(cardBits, col4)));
            case "Coverall":
                return (col0.equals(and(cardBits, col0)) &&
                        col1.equals(and(cardBits, col1)) &&
                        col2.equals(and(cardBits, col2)) &&
                        col3.equals(and(cardBits, col3)) &&
                        col4.equals(and(cardBits, col4)));
            case "Diagonal":
                return (updiagonal0.equals(and(cardBits, updiagonal0)) ||
                        downdiagonal0.equals(and(cardBits, downdiagonal0)));
            case "Diamond":
                return (diamond0.equals(and(cardBits, diamond0)));
            case "DiamondInside":
                return (diamondinside0.equals(and(cardBits, diamondinside0)));
            case "DiamondOutline":
                return (diamondoutline0.equals(and(cardBits, diamondoutline0)));
            case "DollarSign":
                return (dollarsign0.equals(and(cardBits, dollarsign0)));
            case "DoubleBingo":
                return  ((col0.equals(and(cardBits, col0)) &&
                        (row0.equals(and(cardBits, row0)) ||
                         row1.equals(and(cardBits, row1)) ||
                         row2.equals(and(cardBits, row2)) ||
                         row3.equals(and(cardBits, row3)) ||
                         row4.equals(and(cardBits, row4)) ||
                         downdiagonal0.equals(and(cardBits, downdiagonal0)) ||
                         updiagonal0.equals(and(cardBits, updiagonal0)))) ||
                        
                        (col1.equals(and(cardBits, col1)) &&
                        (row0.equals(and(cardBits, row0)) ||
                         row1.equals(and(cardBits, row1)) ||
                         row2.equals(and(cardBits, row2)) ||
                         row3.equals(and(cardBits, row3)) ||
                         row4.equals(and(cardBits, row4)) ||
                         downdiagonal0.equals(and(cardBits, downdiagonal0)) ||
                         updiagonal0.equals(and(cardBits, updiagonal0)))) ||
                        
                        (col2.equals(and(cardBits, col2)) &&
                        (row0.equals(and(cardBits, row0)) ||
                         row1.equals(and(cardBits, row1)) ||
                         row2.equals(and(cardBits, row2)) ||
                         row3.equals(and(cardBits, row3)) ||
                         row4.equals(and(cardBits, row4)) ||
                         downdiagonal0.equals(and(cardBits, downdiagonal0)) ||
                         updiagonal0.equals(and(cardBits, updiagonal0)))) ||
                        
                        (col3.equals(and(cardBits, col3)) &&
                        (row0.equals(and(cardBits, row0)) ||
                         row1.equals(and(cardBits, row1)) ||
                         row2.equals(and(cardBits, row2)) ||
                         row3.equals(and(cardBits, row3)) ||
                         row4.equals(and(cardBits, row4)) ||
                         downdiagonal0.equals(and(cardBits, downdiagonal0)) ||
                         updiagonal0.equals(and(cardBits, updiagonal0)))) ||
                        
                        (col4.equals(and(cardBits, col4)) &&
                        (row0.equals(and(cardBits, row0)) ||
                         row1.equals(and(cardBits, row1)) ||
                         row2.equals(and(cardBits, row2)) ||
                         row3.equals(and(cardBits, row3)) ||
                         row4.equals(and(cardBits, row4)) ||
                         downdiagonal0.equals(and(cardBits, downdiagonal0)) ||
                         updiagonal0.equals(and(cardBits, updiagonal0)))) ||
                        
                        (row0.equals(and(cardBits, row0)) &&
                        (downdiagonal0.equals(and(cardBits, downdiagonal0)) ||
                        updiagonal0.equals(and(cardBits, updiagonal0)))) ||
                        
                        (row1.equals(and(cardBits, row1)) &&
                        (downdiagonal0.equals(and(cardBits, downdiagonal0)) ||
                        updiagonal0.equals(and(cardBits, updiagonal0)))) ||
                        
                        (row2.equals(and(cardBits, row2)) &&
                        (downdiagonal0.equals(and(cardBits, downdiagonal0)) ||
                        updiagonal0.equals(and(cardBits, updiagonal0)))) ||
                        
                        (row3.equals(and(cardBits, row3)) &&
                        (downdiagonal0.equals(and(cardBits, downdiagonal0)) ||
                        updiagonal0.equals(and(cardBits, updiagonal0)))) ||
                        
                        (row4.equals(and(cardBits, row4)) &&
                        (downdiagonal0.equals(and(cardBits, downdiagonal0)) ||
                        updiagonal0.equals(and(cardBits, updiagonal0)))));
            case "DoubleOutside":
                return ((col0.equals(and(cardBits, col0)) &&
                        (col4.equals(and(cardBits, col4)) ||
                         row0.equals(and(cardBits, row0)) ||
                         row4.equals(and(cardBits, row4)))) ||

                        (col4.equals(and(cardBits, col4)) &&
                        (row0.equals(and(cardBits, row0)) ||
                         row4.equals(and(cardBits, row4)))) ||
                        
                        (row0.equals(and(cardBits, row0)) &&
                         row4.equals(and(cardBits, row4))));
            case "DownDiagonal":
                return (downdiagonal0.equals(and(cardBits, downdiagonal0)));
            case "FieldGoal":
                return (fieldgoal0.equals(and(cardBits, fieldgoal0)));
            case "FourCornersBig":
                return (fourcornersbig0.equals(and(cardBits, fourcornersbig0)));
            case "FourCornersSmall":
                return (fourcornerssmall0.equals(and(cardBits, fourcornerssmall0)));
            case "FrameInside":
                return (frameinside0.equals(and(cardBits, frameinside0)));
            case "FrameOutside":
                return (col0.equals(and(cardBits, col0)) &&
                        col4.equals(and(cardBits, col4)) &&
                        row0.equals(and(cardBits, row0)) &&
                        row4.equals(and(cardBits, row4)));
            case "GoBingo":
                return (col3.equals(and(cardBits, col3)) &&
                        col4.equals(and(cardBits, col4)));
            case "Kite":
                return (kite0.equals(and(cardBits, kite0)) ||
                        kite1.equals(and(cardBits, kite1)));
            case "Ladder":
                return (ladder0.equals(and(cardBits, ladder0)) ||
                        ladder1.equals(and(cardBits, ladder1)) ||
                        ladder2.equals(and(cardBits, ladder2)));
            case "LayerCake":
                return (row0.equals(and(cardBits, row0)) &&
                        row2.equals(and(cardBits, row2)) &&
                        row4.equals(and(cardBits, row4)));
            case "LetterC":
                return (row0.equals(and(cardBits, row0)) &&
                        col0.equals(and(cardBits, col0)) &&
                        row4.equals(and(cardBits, row4)));
            case "LetterH":
                return (col0.equals(and(cardBits, col0)) &&
                        row2.equals(and(cardBits, row2)) &&
                        col4.equals(and(cardBits, col4)));
            case "LetterI":
                return (row0.equals(and(cardBits, row0)) &&
                        col2.equals(and(cardBits, col2)) &&
                        row4.equals(and(cardBits, row4)));
            case "LetterL":
                return (col0.equals(and(cardBits, col0)) &&
                        row4.equals(and(cardBits, row4)));
            case "LetterM":
                return (letterm0.equals(and(cardBits, letterm0)));
            case "LetterN":
                return (col0.equals(and(cardBits, col0)) &&
                        downdiagonal0.equals(and(cardBits, downdiagonal0)) &&
                        col4.equals(and(cardBits, col4)));
            case "LetterP":
                return (letterp0.equals(and(cardBits, letterp0)));
            case "LetterS":
                return (letters0.equals(and(cardBits, letters0)));
            case "LetterT":
                return (row0.equals(and(cardBits, row0)) &&
                        col2.equals(and(cardBits, col2)));
            case "LetterW":
                return (letterw0.equals(and(cardBits, letterw0)));
            case "LetterX":
                return (downdiagonal0.equals(and(cardBits, downdiagonal0)) &&
                        updiagonal0.equals(and(cardBits, updiagonal0)));
            case "LetterY":
                return (lettery0.equals(and(cardBits, lettery0)));
            case "LetterZ":
                return (row0.equals(and(cardBits, row0)) &&
                        updiagonal0.equals(and(cardBits, updiagonal0)) &&
                        row4.equals(and(cardBits, row4)));
            case "LoveLetter":
                return (loveletter0.equals(and(cardBits, loveletter0)));
            case "Lucky7":
                return (row0.equals(and(cardBits, row0)) &&
                        updiagonal0.equals(and(cardBits, updiagonal0)));
            case "PercentSign":
                return (percentsign0.equals(and(cardBits, percentsign0)));
            case "PicnicTable":
                return (row0.equals(and(cardBits, row0)) &&
                        updiagonal0.equals(and(cardBits, updiagonal0)) &&
                        downdiagonal0.equals(and(cardBits, downdiagonal0)));
            case "PlusSign":
                return (row2.equals(and(cardBits, row2)) &&
                        col2.equals(and(cardBits, col2)));
            case "Pyramid":
                return (pyramid0.equals(and(cardBits, pyramid0)));
            case "RailroadTracks":
                return (col1.equals(and(cardBits, col1)) &&
                        col3.equals(and(cardBits, col3)));
            case "Row":
                return (row0.equals(and(cardBits, row0)) ||
                        row1.equals(and(cardBits, row1)) ||
                        row2.equals(and(cardBits, row2)) ||
                        row3.equals(and(cardBits, row3)) ||
                        row4.equals(and(cardBits, row4)));
            case "Sputnik":
                return (sputnik0.equals(and(cardBits, sputnik0)));
            case "Stamp1":
                return (stampone0.equals(and(cardBits, stampone0)) ||
                        stampone1.equals(and(cardBits, stampone1)) ||
                        stampone2.equals(and(cardBits, stampone2)) ||
                        stampone3.equals(and(cardBits, stampone3)));
            case "Stamp2":
                return (stamptwo0.equals(and(cardBits, stamptwo0)) ||
                        stamptwo1.equals(and(cardBits, stamptwo1)) ||
                        stamptwo2.equals(and(cardBits, stamptwo2)) ||
                        stamptwo3.equals(and(cardBits, stamptwo3)) ||
                        stamptwo4.equals(and(cardBits, stamptwo4)) ||
                        stamptwo5.equals(and(cardBits, stamptwo5)));
            case "Stamp4":
                return (stampfour0.equals(and(cardBits, stampfour0)));
            case "StampAnd3Corners":
                return (stampand3corners0.equals(and(cardBits, stampand3corners0)) ||
                        stampand3corners1.equals(and(cardBits, stampand3corners1)) ||
                        stampand3corners2.equals(and(cardBits, stampand3corners2)) ||
                        stampand3corners3.equals(and(cardBits, stampand3corners3)));
            case "StampAndLine":
                return (stampandline0.equals(and(cardBits, stampandline0)) ||
                        stampandline1.equals(and(cardBits, stampandline1)) ||
                        stampandline2.equals(and(cardBits, stampandline2)) ||
                        stampandline3.equals(and(cardBits, stampandline3)));
            case "StraightAndCorners":
                return (straightandcorners0.equals(and(cardBits, straightandcorners0)) ||
                        straightandcorners1.equals(and(cardBits, straightandcorners1)));
            case "TelephonePole":
                return (row0.equals(and(cardBits, row0)) &&
                        row2.equals(and(cardBits, row2)) &&
                        col2.equals(and(cardBits, col2)));
            case "TopAndBottom":
                return (row0.equals(and(cardBits, row0)) &&
                        row4.equals(and(cardBits, row4)));
            case "TopHat":
                return (tophat0.equals(and(cardBits, tophat0)));
            case "Tree":
                return (tree0.equals(and(cardBits, tree0)));
            case "TripleBingo":
                return ((row0.equals(and(cardBits, row0)) ||
                        row1.equals(and(cardBits, row1)) ||
                        row2.equals(and(cardBits, row2)) ||
                        row3.equals(and(cardBits, row3)) ||
                     row4.equals(and(cardBits, row4))) &&

                    (col0.equals(and(cardBits, col0)) ||
                     col1.equals(and(cardBits, col1)) ||
                     col2.equals(and(cardBits, col2)) ||
                     col3.equals(and(cardBits, col3)) ||
                     col4.equals(and(cardBits, col4))) &&

                    (updiagonal0.equals(and(cardBits, updiagonal0)) ||
                     downdiagonal0.equals(and(cardBits, downdiagonal0))));
            case "Turtle":
                return (turtle0.equals(and(cardBits, turtle0)) ||
                    turtle1.equals(and(cardBits, turtle1)) ||
                    turtle2.equals(and(cardBits, turtle2)) ||
                    turtle3.equals(and(cardBits, turtle3)));
            case "UpDiagonal":
                return (updiagonal0.equals(and(cardBits, updiagonal0)));
            default:
                return false;
        }
    }

}
