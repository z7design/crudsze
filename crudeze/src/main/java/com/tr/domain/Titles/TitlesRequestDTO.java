package com.tr.domain.Titles;

import com.tr.domain.CostCenter.CostCenterRequestDTO;
import com.tr.domain.Enums.TypeTitle;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TitlesRequestDTO {

    private Long titleId;

    private String description;
    
    private TypeTitle typeTitle;

    private List<CostCenterRequestDTO> costCenter;

    private BigDecimal value;

    private Date regsitrationDate;

    private Date referenceDate;

    private Date dueDate;

    private Date paymentDate;

    public Long getTitleId() {
        return titleId;
    }

    public void setTitleId(Long titleId) {
        this.titleId = titleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeTitle getTypeTitle() {
        return typeTitle;
    }

    public void setTypeTitle(TypeTitle typeTitle) {
        this.typeTitle = typeTitle;
    }

    public List<CostCenterRequestDTO> getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(List<CostCenterRequestDTO> costCenter) {
        this.costCenter = costCenter;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Date getRegsitrationDate() {
        return regsitrationDate;
    }

    public void setRegsitrationDate(Date regsitrationDate) {
        this.regsitrationDate = regsitrationDate;
    }

    public Date getReferenceDate() {
        return referenceDate;
    }

    public void setReferenceDate(Date referenceDate) {
        this.referenceDate = referenceDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
