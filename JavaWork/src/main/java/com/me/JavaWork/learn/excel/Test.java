package com.me.JavaWork.learn.excel;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jxl.Workbook;
import jxl.biff.DisplayFormat;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCell;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import net.sf.json.JSONArray;

public class Test {

	private int currentRow = 0;

	private Map convertAlignment = new HashMap() {
		private static final long serialVersionUID = 1L;
		{
			put("left", Alignment.LEFT);
			put("right", Alignment.RIGHT);
			put("center", Alignment.CENTRE);
			put("general", Alignment.GENERAL);
			put("fill", Alignment.FILL);
			put("justify", Alignment.JUSTIFY);
		}
	};

	public static void main(String[] args) {
		Test t = new Test();
		t.downLoadAdsl();
	}

	/**
	 * 绵阳地址池下载
	 **/
	public void downLoadAdsl() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String fileName = "绵阳地址池查询_" + dateFormat.format(new Date());
		;

		String title = "地址池查询明细";

		try {

			// 创建工作薄和工作表
			WritableWorkbook book = Workbook.createWorkbook(new File("E:\\111.xls"));
			// WritableWorkbook book = Workbook.createWorkbook(new File("E:\\111.xls"),
			// Workbook.getWorkbook(new File("E:\\绵阳地址池查询显示表格模板.xls")));

			WritableSheet sheet1 = book.createSheet("高校业务", 0);
			WritableSheet sheet = book.createSheet("家宽业务", 0);

			// 字体样式
			WritableFont wf = new WritableFont(WritableFont.createFont("Tahoma"), 11, WritableFont.NO_BOLD, false);

			// 主要是改变单元格背景、字体、颜色等等
			WritableCellFormat wcf = new WritableCellFormat(wf);
			// 水平居中对齐
			wcf.setAlignment(Alignment.CENTRE);
			// 竖直方向居中对齐
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			// 边框
			wcf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
			wcf.setBackground(jxl.format.Colour.YELLOW);

			// 家宽业务部分
			// 合并表格
			sheet.mergeCells(1, 0, 1, 1);// (列，行，列，行)
			sheet.mergeCells(2, 0, 4, 0);
			sheet.mergeCells(5, 0, 7, 0);
			sheet.mergeCells(8, 0, 10, 0);
			sheet.mergeCells(11, 0, 13, 0);

			// 标头
			Label head0 = new Label(1, 0, "BRAS名称", wcf);
			Label head1 = new Label(2, 0, "CMCC域", wcf);
			Label head2 = new Label(5, 0, "IMS域", wcf);
			Label head3 = new Label(8, 0, "OTV域", wcf);
			Label head4 = new Label(11, 0, "tfgkd域", wcf);

			sheet.addCell(head0);
			sheet.addCell(head1);
			sheet.addCell(head2);
			sheet.addCell(head3);
			sheet.addCell(head4);

			// 标头第二行
			int index = 2;
			for (int i = 0; i < 4; i++) {
				sheet.addCell(new Label(index, 1, "总地址", wcf));
				sheet.addCell(new Label(index + 1, 1, "已使用地址", wcf));
				sheet.addCell(new Label(index + 2, 1, "利用率", wcf));
				index += 3;
			}
			sheet.setColumnView(1, 10);
			sheet.setColumnView(3, 12);
			sheet.setColumnView(6, 12);
			sheet.setColumnView(9, 12);
			sheet.setColumnView(12, 12);

			// 高校业务部分
			Label head10 = new Label(1, 0, "BRAS名称", wcf);
			Label head11 = new Label(2, 0, "高校名称", wcf);
			Label head12 = new Label(3, 0, "域名", wcf);
			Label head13 = new Label(4, 0, "总地址", wcf);
			Label head14 = new Label(5, 0, "已使用地址", wcf);
			Label head15 = new Label(6, 0, "利用率", wcf);

			sheet1.addCell(head10);
			sheet1.addCell(head11);
			sheet1.addCell(head12);
			sheet1.addCell(head13);
			sheet1.addCell(head14);
			sheet1.addCell(head15);

			book.write();
			book.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 插入数据
	 * 
	 * @param sheet
	 * @param tData
	 */
	private void insertDataColum(WritableSheet sheet, JSONArray tData, JSONArray tAlign) {
		if (tData.size() > 0 && tAlign != null) {
			for (int k = tAlign.size() - 1; k < tData.getJSONArray(0).size(); k++) {
				tAlign.add("left");
			}
		}

		for (int i = 0; i < tData.size(); i++) {

			int row = currentRow++;

			JSONArray rowData = (JSONArray) tData.get(i);

			for (int j = 0; j < rowData.size(); j++) {
				WritableCellFormat format = Test.getDataCellFormat(null);

				try {
					if (tAlign != null)
						format.setAlignment((Alignment) convertAlignment.get(tAlign.get(j)));
				} catch (Exception e) {
					e.printStackTrace();
				}

				Test.insertOneCellData(sheet, j, row, rowData.get(j), format);

			}

		}

	}

	/**
	 * 插入单元格数据
	 * 
	 * @param sheet
	 * @param col
	 * @param row
	 * @param data
	 * @param format
	 */
	public static void insertOneCellData(WritableSheet sheet, int col, int row, Object data,
			WritableCellFormat format) {
		try {
			if (data instanceof Integer) {
				jxl.write.Number labelInt = new jxl.write.Number(col, row, ((Integer) data).intValue(), format);
				sheet.addCell(labelInt);
			} else if (data instanceof Double) {
				jxl.write.Number labelNF = new jxl.write.Number(col, row, ((Double) data).doubleValue(), format);
				sheet.addCell(labelNF);
			} else if (data instanceof Boolean) {
				jxl.write.Boolean labelB = new jxl.write.Boolean(col, row, ((Boolean) data).booleanValue(), format);
				sheet.addCell(labelB);
			} else if (data instanceof Date) {
				jxl.write.DateTime labelDT = new jxl.write.DateTime(col, row, (Date) data, format);
				sheet.addCell(labelDT);
				setCellComments(labelDT, "这是个创建表的日期说明！");
			} else if (data != null && data.toString().replaceAll("[\\d]", "").equals("%")) {
				double val = Double.parseDouble(data.toString().replaceAll("%", "")) / 100;
				jxl.write.Number labelNF = new jxl.write.Number(col, row, val,
						Test.getDataCellFormat(NumberFormats.PERCENT_FLOAT));
				sheet.addCell(labelNF);
			} else {
				Label label = new Label(col, row, data.toString(), format);
				sheet.addCell(label);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 给单元格加注释
	 * 
	 * @param label
	 * @param comments
	 */
	public static void setCellComments(Object label, String comments) {
		WritableCellFeatures cellFeatures = new WritableCellFeatures();
		cellFeatures.setComment(comments);
		if (label instanceof jxl.write.Number) {
			jxl.write.Number num = (jxl.write.Number) label;
			num.setCellFeatures(cellFeatures);
		} else if (label instanceof jxl.write.Boolean) {
			jxl.write.Boolean bool = (jxl.write.Boolean) label;
			bool.setCellFeatures(cellFeatures);
		} else if (label instanceof jxl.write.DateTime) {
			jxl.write.DateTime dt = (jxl.write.DateTime) label;
			dt.setCellFeatures(cellFeatures);
		} else {
			Label _label = (Label) label;
			_label.setCellFeatures(cellFeatures);
		}
	}

	/**
	 * 得到数据单元格格式
	 * 
	 * @param dispFormat
	 * @return
	 */
	public static WritableCellFormat getDataCellFormat(DisplayFormat dispFormat) {
		WritableCellFormat wcf = null;
		try {
			WritableFont wf = new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.NO_BOLD, false);
			wf.setColour(Colour.BLACK);

			if (dispFormat != null) {
				wcf = new WritableCellFormat(wf, dispFormat);
			} else {
				wcf = new WritableCellFormat(wf);
			}

			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);

			wcf.setBorder(Border.ALL, BorderLineStyle.THIN);

			wcf.setBackground(Colour.WHITE);
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return wcf;
	}

}
