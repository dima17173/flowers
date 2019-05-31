package com.impltech.reports;

import net.sf.dynamicreports.report.base.expression.AbstractValueFormatter;
import net.sf.dynamicreports.report.builder.ReportTemplateBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.datatype.BigDecimalType;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.builder.tableofcontents.TableOfContentsCustomizerBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;
import net.sf.dynamicreports.report.definition.ReportParameters;

import java.awt.*;
import java.util.Locale;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

/**
 * @author dima
 */
public class Templates {

	public static final StyleBuilder rootStyle;
	public static final StyleBuilder boldStyle;
	public static final StyleBuilder italicStyle;
	public static final StyleBuilder boldCenteredStyle;
	public static final StyleBuilder bold12CenteredStyle;
	public static final StyleBuilder bold18CenteredStyle;
	public static final StyleBuilder bold22CenteredStyle;
	public static final StyleBuilder columnStyle;
	public static final StyleBuilder columnTitleStyle;
	public static final StyleBuilder columnStylePriceList;
	public static final StyleBuilder titleStyle;
	public static final StyleBuilder groupStyle;
	public static final StyleBuilder subtotalStyle;
	public static final StyleBuilder invoiceStyle;
	public static final StyleBuilder invoiceStyle10;
	public static final StyleBuilder boldInvoiceStyle;

	public static final ReportTemplateBuilder reportTemplate;
	public static final ReportTemplateBuilder priceListTemplate;
	public static final ReportTemplateBuilder secondReportTemplate;
	public static final CurrencyType currencyType;
	public static final ComponentBuilder<?, ?> footerComponent;
	public static final ComponentBuilder<?, ?> secondFooterComponent;

	static {
        invoiceStyle = stl.style(3).setFontSize(8);
        invoiceStyle10 = stl.style(invoiceStyle).setFontSize(9).setPadding(2);
        boldInvoiceStyle = stl.style(invoiceStyle).bold();
        rootStyle = stl.style().setPadding(2);
		boldStyle = stl.style(rootStyle).bold();
		italicStyle = stl.style(rootStyle).italic();

		boldCenteredStyle = stl.style(boldStyle)
            .setTextAlignment(HorizontalTextAlignment.CENTER, VerticalTextAlignment.MIDDLE);

		bold12CenteredStyle = stl.style(boldCenteredStyle)
            .setFontSize(12);

		bold18CenteredStyle = stl.style(boldCenteredStyle)
            .setFontSize(18);

		bold22CenteredStyle = stl.style(boldCenteredStyle)
            .setFontSize(22);

        columnStyle = stl.style(rootStyle).setVerticalTextAlignment(VerticalTextAlignment.MIDDLE);

        columnStylePriceList = stl.style(columnStyle.setFontSize(8).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER));

		columnTitleStyle = stl.style(columnStyle)
            .setBorder(stl.pen1Point())
            .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)
            .setBackgroundColor(Color.LIGHT_GRAY)
            .bold();

		titleStyle = stl.style(bold18CenteredStyle);

		groupStyle = stl.style(boldStyle)
            .setHorizontalTextAlignment(HorizontalTextAlignment.LEFT);

		subtotalStyle = stl.style(boldStyle)
		                         .setTopBorder(stl.pen1Point());

		StyleBuilder crosstabGroupStyle = stl.style(columnTitleStyle);

		StyleBuilder crosstabGroupTotalStyle = stl.style(columnTitleStyle)
            .setBackgroundColor(new Color(170, 170, 170));

		StyleBuilder crosstabGrandTotalStyle = stl.style(columnTitleStyle)
            .setBackgroundColor(new Color(140, 140, 140));

		StyleBuilder crosstabCellStyle = stl.style(columnStyle)
            .setBorder(stl.pen1Point());

		TableOfContentsCustomizerBuilder tableOfContentsCustomizer = tableOfContentsCustomizer()
			.setHeadingStyle(0, stl.style(rootStyle).bold());

        secondReportTemplate = template()
            .setLocale(Locale.ENGLISH)
            .setColumnStyle(columnStyle.setFontSize(9).setBorder(stl.pen1Point()).setBackgroundColor(new Color(177, 187, 207)))
            .setColumnTitleStyle(columnTitleStyle.setFontSize(9).setBackgroundColor(new Color(0x99A0B2)))
            .setGroupStyle(groupStyle.setFontSize(9))
            .setGroupTitleStyle(columnTitleStyle.setFontSize(9))
            .setSubtotalStyle(subtotalStyle.setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)
                .setFontSize(9).setBorder(stl.pen1Point()).setBackgroundColor(new Color(0x99A0B2)))
            .highlightDetailEvenRows()
            .crosstabHighlightEvenRows();

		reportTemplate = template()
            .setLocale(Locale.ENGLISH)
            .setColumnStyle(columnStyle)
            .setColumnTitleStyle(columnTitleStyle)
            .setGroupStyle(groupStyle)
            .setGroupTitleStyle(groupStyle)
            .setSubtotalStyle(subtotalStyle)
            .highlightDetailEvenRows()
            .crosstabHighlightEvenRows()
            .setCrosstabGroupStyle(crosstabGroupStyle)
            .setCrosstabGroupTotalStyle(crosstabGroupTotalStyle)
            .setCrosstabGrandTotalStyle(crosstabGrandTotalStyle)
            .setCrosstabCellStyle(crosstabCellStyle)
            .setTableOfContentsCustomizer(tableOfContentsCustomizer);

		priceListTemplate = template()
            .setLocale(Locale.ENGLISH)
            .setTitleStyle(titleStyle)
            .setColumnStyle(columnStylePriceList)
            .setColumnTitleStyle(stl.style(columnStylePriceList).bold())
            .setGroupStyle(groupStyle)
            .setGroupTitleStyle(groupStyle)
            .setSubtotalStyle(subtotalStyle)
            .setPageFormat(PageType.A4, PageOrientation.LANDSCAPE)
            .highlightDetailEvenRows()
            .crosstabHighlightEvenRows()
            .setCrosstabGroupStyle(crosstabGroupStyle)
            .setCrosstabGroupTotalStyle(crosstabGroupTotalStyle)
            .setCrosstabGrandTotalStyle(crosstabGrandTotalStyle)
            .setCrosstabCellStyle(crosstabCellStyle)
            .setTableOfContentsCustomizer(tableOfContentsCustomizer);

		currencyType = new CurrencyType();

		footerComponent = cmp.pageXofY().setStyle(stl.style(boldCenteredStyle)
            .setTopBorder(stl.pen1Point()));
        secondFooterComponent =  cmp.pageXofY().setStyle(bold12CenteredStyle);
	}

    /**
     * Creates custom component which is possible to add to any report band component
     * @param titleReportsComponent
     * @param companyAddressComponents
     * @param companyContactsComponents
     * @return
     */
  public static ComponentBuilder<?, ?>
createTitleComponent(ComponentBuilder<?, ?> titleReportsComponent, ComponentBuilder<?, ?> companyAddressComponents,
        ComponentBuilder<?, ?> companyContactsComponents) {
    return cmp.horizontalList()
        .add(titleReportsComponent)
        .newRow()
        .add(companyAddressComponents)
        .newRow()
        .add(companyContactsComponents)
        .newRow()
        .add(cmp.line())
        .newRow()
        .add(cmp.verticalGap(10));
	}

	public static ComponentBuilder<?, ?>
    createTitleComponent2(ComponentBuilder<?, ?> title1, ComponentBuilder<?, ?> title2, ComponentBuilder<?, ?>  title3) {
    return cmp.verticalList()
        .add(title1)
        .add(cmp.verticalGap(10))
        .add(title2)
        .add(cmp.verticalGap(10))
        .add(title3)
        .add(cmp.verticalGap(10));
	}

	public static ComponentBuilder<?, ?>
    createPresentationList(ComponentBuilder<?, ?> reportComponent1, ComponentBuilder<?, ?> reportComponent2, ComponentBuilder<?, ?> reportComponent3, ComponentBuilder<?, ?> reportComponent4) {
      return cmp.verticalList()
          .add(reportComponent1)
          .add(cmp.line())
          .add(cmp.verticalGap(10))
          .add(reportComponent2)
          .add(cmp.verticalGap(20))
          .add(reportComponent3)
          .add(cmp.verticalGap(10))
          .add(cmp.line())
          .add(cmp.verticalGap(10))
          .add(reportComponent4)
          .add(cmp.verticalGap(20))
          .add(cmp.line());
    }

    /**
     *
     * @param label
     * @return
     */
	public static CurrencyValueFormatter createCurrencyValueFormatter(String label) {
		return new CurrencyValueFormatter(label);
	}


	public static class CurrencyType extends BigDecimalType {

		private static final long serialVersionUID = 1L;

		@Override
		public String getPattern() {
			return "$ #,###.00";
		}
	}

	private static class CurrencyValueFormatter extends AbstractValueFormatter<String, Number> {

		private static final long serialVersionUID = 1L;

		private String label;

		public CurrencyValueFormatter(String label) {
			this.label = label;
		}

        /**
         *
         * @param value
         * @param reportParameters
         * @return
         */
		@Override
		public String format(Number value, ReportParameters reportParameters) {
			return label + currencyType.valueToString(value, reportParameters.getLocale());
		}
	}
}
