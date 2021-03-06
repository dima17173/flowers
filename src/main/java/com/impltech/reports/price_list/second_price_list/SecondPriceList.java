package com.impltech.reports.price_list.second_price_list;

import com.impltech.reports.balance.second_balance.BalanceTemplates;
import com.impltech.reports.invoice.second_template.SecondInvoiceData;
import com.impltech.web.rest.util.ReportsUtils;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

/**
 * Created by alex
 */
public class SecondPriceList {

    public static ComponentBuilder<?, ?> titleReportsComponent;
    public static ComponentBuilder<?, ?> titleReportsComponent1;
    private SecondInvoiceData invoiceData = new SecondInvoiceData();

    public static void secondPriceListReportBuilder(String path) throws FileNotFoundException {
        try {
            new SecondPriceList().build().toPdf(new FileOutputStream(path));
        } catch (DRException e) {
            e.printStackTrace();
        }
    }

    public JasperReportBuilder build() throws FileNotFoundException {
        JasperReportBuilder report = report();

        TextColumnBuilder<String> itemColumn = col.column("Variedad", "variety", type.stringType());
        TextColumnBuilder<BigDecimal> fortyColumn = col.column("40", "forty", type.bigDecimalType());
        TextColumnBuilder<BigDecimal> fiftyColumn = col.column("50", "fifty", type.bigDecimalType());
        TextColumnBuilder<BigDecimal> sixtyColumn = col.column("60", "sixty", type.bigDecimalType());
        TextColumnBuilder<BigDecimal> seventyColumn = col.column("70", "seventy", type.bigDecimalType());
        TextColumnBuilder<BigDecimal> eightyColumn = col.column("80", "eighty", type.bigDecimalType());
        TextColumnBuilder<BigDecimal> ninetyColumn = col.column("90", "ninety", type.bigDecimalType());
        TextColumnBuilder<BigDecimal> houndredColumn = col.column("100", "hundred", type.bigDecimalType());
        TextColumnBuilder<BigDecimal> houndredTenColumn = col.column("110", "hundred_ten", type.bigDecimalType());
        TextColumnBuilder<BigDecimal> houndredTwentyColumn = col.column("120", "hundred_twenty", type.bigDecimalType());
        TextColumnBuilder<BigDecimal> houndredFiftyColumn = col.column("150", "hundred_fifty", type.bigDecimalType());
        TextColumnBuilder<BigDecimal> twoHundredColumn = col.column("200", "two_hundred", type.bigDecimalType());

        titleReportsComponent =
            cmp.horizontalList(
                cmp.image(ReportsUtils.getImagePath().toString()).setFixedHeight(80));

        titleReportsComponent1 =
            cmp.horizontalList(
                cmp.text(invoiceData.getSecondInvoice().getCompanyName() + "\n"
                    + invoiceData.getSecondInvoice().getSeason() + " "
                    + invoiceData.getSecondInvoice().getStartSeason() + " - "
                    + invoiceData.getSecondInvoice().getEndSeason() +
                    "\nPrice List").setStyle(BalanceTemplates.boldCenteredStyle10))
                .setBackgroundComponent(BalanceTemplates.rectangleBackground);

        HorizontalListBuilder title = cmp.horizontalList()
            .add(titleReportsComponent, titleReportsComponent1);

        report
            .setTemplate(BalanceTemplates.reportTemplate)
            .columns(itemColumn, fortyColumn, fiftyColumn, sixtyColumn, seventyColumn, eightyColumn, ninetyColumn,
                houndredColumn, houndredTenColumn, houndredTwentyColumn, houndredFiftyColumn, twoHundredColumn)
            .title(title, cmp.verticalGap(10))
            .pageFooter(cmp.pageXofY().setStyle(BalanceTemplates.footerStyle))
            .sortBy(asc(itemColumn))
            .setDataSource(createSecondDataSource());
        return report;
    }

    private JRDataSource createSecondDataSource() {

        DRDataSource secondDataSource = new DRDataSource("variety", "fourty", "fifty", "sixty", "seventy", "eighty", "ninety",
            "houndred", "huendred_ten", "houndred_twenty", "houndred_fifty", "two_hundred");

        List<String> flowersName = new ArrayList<>(Arrays.asList("Freedom", "Explorer", "Mondial", "Vendela",
            "3D", "Iguana", "High & Magic", "Red Intuition"));

        for (String flower : flowersName) {
            secondDataSource.add(flower, new BigDecimal(15), new BigDecimal(15), new BigDecimal(15), new BigDecimal(15), new BigDecimal(15),
                new BigDecimal(15), new BigDecimal(15), new BigDecimal(15), new BigDecimal(15), new BigDecimal(15),
                new BigDecimal(15));
        }
        return secondDataSource;
    }
}
