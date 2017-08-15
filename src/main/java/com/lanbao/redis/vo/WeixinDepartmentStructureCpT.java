package com.lanbao.redis.vo;

// Generated 2014-10-9 18:51:28 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * WeixinDepartmentStructureCpT generated by hbm2java
 */
@Entity
@Table(name = "weixin_department_structure_cp_t", catalog = "jshoper3")
public class WeixinDepartmentStructureCpT implements java.io.Serializable {

	private String id;
	private String wxgivenid;
	private String name;
	private String parentid;
	private String grade;
	private String path;
	private int sort;
	private String code;
	private String jshopid;
	private String jshopname;
	private Date createtime;
	private String parentname;
	private Date updatetime;
	private int versiont;
	private String logo;
	private String status;
	private String mobilesync;
	private int order;

	public WeixinDepartmentStructureCpT() {
	}

	public WeixinDepartmentStructureCpT(String id, String name,
			String parentid, String grade, String path, int sort,
			Date createtime, String parentname, Date updatetime, int versiont,
			String logo, String status, String mobilesync, int order) {
		this.id = id;
		this.name = name;
		this.parentid = parentid;
		this.grade = grade;
		this.path = path;
		this.sort = sort;
		this.createtime = createtime;
		this.parentname = parentname;
		this.updatetime = updatetime;
		this.versiont = versiont;
		this.logo = logo;
		this.status = status;
		this.mobilesync = mobilesync;
		this.order = order;
	}

	public WeixinDepartmentStructureCpT(String id, String wxgivenid,
			String name, String parentid, String grade, String path, int sort,
			String code, String jshopid, String jshopname, Date createtime,
			String parentname, Date updatetime, int versiont, String logo,
			String status, String mobilesync, int order) {
		this.id = id;
		this.wxgivenid = wxgivenid;
		this.name = name;
		this.parentid = parentid;
		this.grade = grade;
		this.path = path;
		this.sort = sort;
		this.code = code;
		this.jshopid = jshopid;
		this.jshopname = jshopname;
		this.createtime = createtime;
		this.parentname = parentname;
		this.updatetime = updatetime;
		this.versiont = versiont;
		this.logo = logo;
		this.status = status;
		this.mobilesync = mobilesync;
		this.order = order;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 20)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "WXGIVENID", length = 10)
	public String getWxgivenid() {
		return this.wxgivenid;
	}

	public void setWxgivenid(String wxgivenid) {
		this.wxgivenid = wxgivenid;
	}

	@Column(name = "NAME", nullable = false, length = 60)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PARENTID", nullable = false, length = 10)
	public String getParentid() {
		return this.parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	@Column(name = "GRADE", nullable = false, length = 1)
	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Column(name = "PATH", nullable = false, length = 200)
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "SORT", nullable = false)
	public int getSort() {
		return this.sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	@Column(name = "CODE", length = 45)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "JSHOPID", length = 20)
	public String getJshopid() {
		return this.jshopid;
	}

	public void setJshopid(String jshopid) {
		this.jshopid = jshopid;
	}

	@Column(name = "JSHOPNAME", length = 45)
	public String getJshopname() {
		return this.jshopname;
	}

	public void setJshopname(String jshopname) {
		this.jshopname = jshopname;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME", nullable = false, length = 0)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "PARENTNAME", nullable = false, length = 60)
	public String getParentname() {
		return this.parentname;
	}

	public void setParentname(String parentname) {
		this.parentname = parentname;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATETIME", nullable = false, length = 0)
	public Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	@Column(name = "VERSIONT", nullable = false)
	public int getVersiont() {
		return this.versiont;
	}

	public void setVersiont(int versiont) {
		this.versiont = versiont;
	}

	@Column(name = "LOGO", nullable = false)
	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Column(name = "STATUS", nullable = false, length = 45)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "MOBILESYNC", nullable = false, length = 1)
	public String getMobilesync() {
		return this.mobilesync;
	}

	public void setMobilesync(String mobilesync) {
		this.mobilesync = mobilesync;
	}

	@Column(name = "ORDER", nullable = false)
	public int getOrder() {
		return this.order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

}
